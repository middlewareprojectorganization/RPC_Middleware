/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.config.utils;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.URLBuilder;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;

import org.apache.dubbo.common.status.StatusChecker;
import org.apache.dubbo.common.threadpool.ThreadPool;
import org.apache.dubbo.common.utils.*;
import org.apache.dubbo.config.*;

import org.apache.dubbo.registry.RegistryService;
import org.apache.dubbo.remoting.Codec;
import org.apache.dubbo.remoting.Dispatcher;
import org.apache.dubbo.remoting.Transporter;

import org.apache.dubbo.rpc.ExporterListener;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.InvokerListener;
import org.apache.dubbo.rpc.ProxyFactory;
import org.apache.dubbo.rpc.cluster.LoadBalance;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.dubbo.common.constants.CommonConstants.*;
import static org.apache.dubbo.common.constants.RegistryConstants.*;
import static org.apache.dubbo.common.extension.ExtensionLoader.getExtensionLoader;
import static org.apache.dubbo.common.utils.UrlUtils.isServiceDiscoveryRegistryType;
import static org.apache.dubbo.config.Constants.*;
import static org.apache.dubbo.registry.Constants.REGISTER_KEY;
import static org.apache.dubbo.registry.Constants.*;
import static org.apache.dubbo.remoting.Constants.*;
import static org.apache.dubbo.rpc.cluster.Constants.REFER_KEY;

public class ConfigValidationUtils {
    private static final Logger logger = LoggerFactory.getLogger(ConfigValidationUtils.class);
    /**
     * The maximum length of a <b>parameter's value</b>
     */
    private static final int MAX_LENGTH = 200;

    /**
     * The maximum length of a <b>path</b>
     */
    private static final int MAX_PATH_LENGTH = 200;

    /**
     * The rule qualification for <b>name</b>
     */
    private static final Pattern PATTERN_NAME = Pattern.compile("[\\-._0-9a-zA-Z]+");

    /**
     * The rule qualification for <b>multiply name</b>
     */
    private static final Pattern PATTERN_MULTI_NAME = Pattern.compile("[,\\-._0-9a-zA-Z]+");

    /**
     * The rule qualification for <b>method names</b>
     */
    private static final Pattern PATTERN_METHOD_NAME = Pattern.compile("[a-zA-Z][0-9a-zA-Z]*");

    /**
     * The rule qualification for <b>path</b>
     */
    private static final Pattern PATTERN_PATH = Pattern.compile("[/\\-$._0-9a-zA-Z]+");

    /**
     * The pattern matches a value who has a symbol
     */
    private static final Pattern PATTERN_NAME_HAS_SYMBOL = Pattern.compile("[:*,\\s/\\-._0-9a-zA-Z]+");

    /**
     * The pattern matches a property key
     */
    private static final Pattern PATTERN_KEY = Pattern.compile("[*,\\-._0-9a-zA-Z]+");


    public static List<URL> loadRegistries(AbstractInterfaceConfig interfaceConfig, boolean provider) {
        // check && override if necessary
        List<URL> registryList = new ArrayList<URL>();
        ApplicationConfig application = interfaceConfig.getApplication();
        List<RegistryConfig> registries = interfaceConfig.getRegistries();
        if (CollectionUtils.isNotEmpty(registries)) {
            for (RegistryConfig config : registries) {
                String address = config.getAddress();
                if (StringUtils.isEmpty(address)) {
                    address = ANYHOST_VALUE;
                }
                if (!RegistryConfig.NO_AVAILABLE.equalsIgnoreCase(address)) {
                    Map<String, String> map = new HashMap<String, String>();
                    AbstractConfig.appendParameters(map, application);
                    AbstractConfig.appendParameters(map, config);
                    map.put(PATH_KEY, RegistryService.class.getName());
                    AbstractInterfaceConfig.appendRuntimeParameters(map);
                    if (!map.containsKey(PROTOCOL_KEY)) {
                        map.put(PROTOCOL_KEY, DUBBO_PROTOCOL);
                    }
                    List<URL> urls = UrlUtils.parseURLs(address, map);

                    for (URL url : urls) {

                        url = URLBuilder.from(url)
                                .addParameter(REGISTRY_KEY, url.getProtocol())
                                .setProtocol(extractRegistryType(url))
                                .build();
                        if ((provider && url.getParameter(REGISTER_KEY, true))
                                || (!provider && url.getParameter(SUBSCRIBE_KEY, true))) {
                            registryList.add(url);
                        }
                    }
                }
            }
        }
        return registryList;
    }

    public static URL loadMonitor(AbstractInterfaceConfig interfaceConfig, URL registryURL) {
      return  null;
    }

    /**
     * Legitimacy check and setup of local simulated operations. The operations can be a string with Simple operation or
     * a classname whose {@link Class} implements a particular function
     *
     * @param interfaceClass for provider side, it is the {@link Class} of the service that will be exported; for consumer
     *                       side, it is the {@link Class} of the remote service interface that will be referenced
     */
    public static void checkMock(Class<?> interfaceClass, AbstractInterfaceConfig config) {

    }

    public static void validateAbstractInterfaceConfig(AbstractInterfaceConfig config) {

    }

    public static void validateServiceConfig(ServiceConfig config) {


    }

    public static void validateReferenceConfig(ReferenceConfig config) {
        checkMultiExtension(InvokerListener.class, "listener", config.getListener());
        checkKey(VERSION_KEY, config.getVersion());
        checkKey(GROUP_KEY, config.getGroup());
        checkName(CLIENT_KEY, config.getClient());

        validateAbstractInterfaceConfig(config);

        List<RegistryConfig> registries = config.getRegistries();
        if (registries != null) {
            for (RegistryConfig registry : registries) {
                validateRegistryConfig(registry);
            }
        }

        ConsumerConfig consumerConfig = config.getConsumer();
        if (consumerConfig != null) {
            validateConsumerConfig(consumerConfig);
        }
    }

    public static void validateConfigCenterConfig(ConfigCenterConfig config) {
        if (config != null) {
            checkParameterName(config.getParameters());
        }
    }

    public static void validateApplicationConfig(ApplicationConfig config) {
        if (config == null) {
            return;
        }

        if (!config.isValid()) {
            throw new IllegalStateException("No application config found or it's not a valid config! " +
                    "Please add <dubbo:application name=\"...\" /> to your spring config.");
        }

        // backward compatibility
        String wait = ConfigUtils.getProperty(SHUTDOWN_WAIT_KEY);
        if (wait != null && wait.trim().length() > 0) {
            System.setProperty(SHUTDOWN_WAIT_KEY, wait.trim());
        } else {
            wait = ConfigUtils.getProperty(SHUTDOWN_WAIT_SECONDS_KEY);
            if (wait != null && wait.trim().length() > 0) {
                System.setProperty(SHUTDOWN_WAIT_SECONDS_KEY, wait.trim());
            }
        }

        checkName(NAME, config.getName());
        checkMultiName(OWNER, config.getOwner());
        checkName(ORGANIZATION, config.getOrganization());
        checkName(ARCHITECTURE, config.getArchitecture());
        checkName(ENVIRONMENT, config.getEnvironment());
        checkParameterName(config.getParameters());
    }

    public static void validateModuleConfig(ModuleConfig config) {
        if (config != null) {
            checkName(NAME, config.getName());
            checkName(OWNER, config.getOwner());
            checkName(ORGANIZATION, config.getOrganization());
        }
    }

    public static void validateMetadataConfig(MetadataReportConfig metadataReportConfig) {
        if (metadataReportConfig == null) {
            return;
        }
    }

    public static void validateMetricsConfig(MetricsConfig metricsConfig) {
        if (metricsConfig == null) {
            return;
        }
    }

    public static void validateSslConfig(SslConfig sslConfig) {
        if (sslConfig == null) {
            return;
        }
    }

    public static void validateMonitorConfig(MonitorConfig config) {
        if (config != null) {
            if (!config.isValid()) {
                logger.info("There's no valid monitor config found, if you want to open monitor statistics for Dubbo, " +
                        "please make sure your monitor is configured properly.");
            }

            checkParameterName(config.getParameters());
        }
    }

    public static void validateProtocolConfig(ProtocolConfig config) {
    }

    public static void validateProviderConfig(ProviderConfig config) {

    }

    public static void validateConsumerConfig(ConsumerConfig config) {
        if (config == null) {
            return;
        }
    }

    public static void validateRegistryConfig(RegistryConfig config) {
        checkName(PROTOCOL_KEY, config.getProtocol());
        checkName(USERNAME_KEY, config.getUsername());
        checkLength(PASSWORD_KEY, config.getPassword());
        checkPathLength(FILE_KEY, config.getFile());
        checkName(TRANSPORTER_KEY, config.getTransporter());
        checkName(SERVER_KEY, config.getServer());
        checkName(CLIENT_KEY, config.getClient());
        checkParameterName(config.getParameters());
    }

    public static void validateMethodConfig(MethodConfig config) {

    }

    private static String extractRegistryType(URL url) {
        return isServiceDiscoveryRegistryType(url) ? SERVICE_REGISTRY_PROTOCOL : REGISTRY_PROTOCOL;
    }

    public static void checkExtension(Class<?> type, String property, String value) {
        checkName(property, value);
        if (StringUtils.isNotEmpty(value)
                && !ExtensionLoader.getExtensionLoader(type).hasExtension(value)) {
            throw new IllegalStateException("No such extension " + value + " for " + property + "/" + type.getName());
        }
    }

    /**
     * Check whether there is a <code>Extension</code> who's name (property) is <code>value</code> (special treatment is
     * required)
     *
     * @param type     The Extension type
     * @param property The extension key
     * @param value    The Extension name
     */
    public static void checkMultiExtension(Class<?> type, String property, String value) {
        checkMultiName(property, value);
        if (StringUtils.isNotEmpty(value)) {
            String[] values = value.split("\\s*[,]+\\s*");
            for (String v : values) {
                if (v.startsWith(REMOVE_VALUE_PREFIX)) {
                    v = v.substring(1);
                }
                if (DEFAULT_KEY.equals(v)) {
                    continue;
                }
                if (!ExtensionLoader.getExtensionLoader(type).hasExtension(v)) {
                    throw new IllegalStateException("No such extension " + v + " for " + property + "/" + type.getName());
                }
            }
        }
    }

    public static void checkLength(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, null);
    }

    public static void checkPathLength(String property, String value) {
        checkProperty(property, value, MAX_PATH_LENGTH, null);
    }

    public static void checkName(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, PATTERN_NAME);
    }

    public static void checkNameHasSymbol(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, PATTERN_NAME_HAS_SYMBOL);
    }

    public static void checkKey(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, PATTERN_KEY);
    }

    public static void checkMultiName(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, PATTERN_MULTI_NAME);
    }

    public static void checkPathName(String property, String value) {
        checkProperty(property, value, MAX_PATH_LENGTH, PATTERN_PATH);
    }

    public static void checkMethodName(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, PATTERN_METHOD_NAME);
    }

    public static void checkParameterName(Map<String, String> parameters) {
        if (CollectionUtils.isEmptyMap(parameters)) {
            return;
        }
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            checkNameHasSymbol(entry.getKey(), entry.getValue());
        }
    }

    public static void checkProperty(String property, String value, int maxlength, Pattern pattern) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        if (value.length() > maxlength) {
            throw new IllegalStateException("Invalid " + property + "=\"" + value + "\" is longer than " + maxlength);
        }
        if (pattern != null) {
            Matcher matcher = pattern.matcher(value);
            if (!matcher.matches()) {
                throw new IllegalStateException("Invalid " + property + "=\"" + value + "\" contains illegal " +
                        "character, only digit, letter, '-', '_' or '.' is legal.");
            }
        }
    }

}
