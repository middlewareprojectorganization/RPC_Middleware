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
package com.xxy.rpc.rpc.cluster.configurator;

import com.xxy.rpc.common.constants.CommonConstants;
import com.xxy.rpc.common.constants.RegistryConstants;
import com.xxy.rpc.common.utils.NetUtils;
import com.xxy.rpc.common.utils.StringUtils;
import com.xxy.rpc.rpc.cluster.Configurator;
import com.xxy.rpc.rpc.cluster.Constants;
import com.xxy.rpc.common.URL;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * AbstractOverrideConfigurator
 */
public abstract class AbstractConfigurator implements Configurator {

    private final URL configuratorUrl;

    public AbstractConfigurator(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("configurator url == null");
        }
        this.configuratorUrl = url;
    }

    @Override
    public URL getUrl() {
        return configuratorUrl;
    }

    @Override
    public URL configure(URL url) {
        // If override url is not enabled or is invalid, just return.
        if (!configuratorUrl.getParameter(CommonConstants.ENABLED_KEY, true) || configuratorUrl.getHost() == null || url == null || url.getHost() == null) {
            return url;
        }
        /**
         * This if branch is created since 2.7.0.
         */
        String apiVersion = configuratorUrl.getParameter(Constants.CONFIG_VERSION_KEY);
        if (StringUtils.isNotEmpty(apiVersion)) {
            String currentSide = url.getParameter(CommonConstants.SIDE_KEY);
            String configuratorSide = configuratorUrl.getParameter(CommonConstants.SIDE_KEY);
            if (currentSide.equals(configuratorSide) && CommonConstants.CONSUMER.equals(configuratorSide) && 0 == configuratorUrl.getPort()) {
                url = configureIfMatch(NetUtils.getLocalHost(), url);
            } else if (currentSide.equals(configuratorSide) && CommonConstants.PROVIDER.equals(configuratorSide) && url.getPort() == configuratorUrl.getPort()) {
                url = configureIfMatch(url.getHost(), url);
            }
        }
        /**
         * This else branch is deprecated and is left only to keep compatibility with versions before 2.7.0
         */
        else {
            url = configureDeprecated(url);
        }
        return url;
    }

    @Deprecated
    private URL configureDeprecated(URL url) {
        // If override url has port, means it is a provider address. We want to control a specific provider with this override url, it may take effect on the specific provider instance or on consumers holding this provider instance.
        if (configuratorUrl.getPort() != 0) {
            if (url.getPort() == configuratorUrl.getPort()) {
                return configureIfMatch(url.getHost(), url);
            }
        } else {// override url don't have a port, means the ip override url specify is a consumer address or 0.0.0.0
            // 1.If it is a consumer ip address, the intention is to control a specific consumer instance, it must takes effect at the consumer side, any provider received this override url should ignore;
            // 2.If the ip is 0.0.0.0, this override url can be used on consumer, and also can be used on provider
            if (url.getParameter(CommonConstants.SIDE_KEY, CommonConstants.PROVIDER).equals(CommonConstants.CONSUMER)) {
                return configureIfMatch(NetUtils.getLocalHost(), url);// NetUtils.getLocalHost is the ip address consumer registered to registry.
            } else if (url.getParameter(CommonConstants.SIDE_KEY, CommonConstants.CONSUMER).equals(CommonConstants.PROVIDER)) {
                return configureIfMatch(CommonConstants.ANYHOST_VALUE, url);// take effect on all providers, so address must be 0.0.0.0, otherwise it won't flow to this if branch
            }
        }
        return url;
    }

    private URL configureIfMatch(String host, URL url) {
        if (CommonConstants.ANYHOST_VALUE.equals(configuratorUrl.getHost()) || host.equals(configuratorUrl.getHost())) {
            // TODO, to support wildcards
            String providers = configuratorUrl.getParameter(Constants.OVERRIDE_PROVIDERS_KEY);
            if (StringUtils.isEmpty(providers) || providers.contains(url.getAddress()) || providers.contains(CommonConstants.ANYHOST_VALUE)) {
                String configApplication = configuratorUrl.getParameter(CommonConstants.APPLICATION_KEY,
                        configuratorUrl.getUsername());
                String currentApplication = url.getParameter(CommonConstants.APPLICATION_KEY, url.getUsername());
                if (configApplication == null || CommonConstants.ANY_VALUE.equals(configApplication)
                        || configApplication.equals(currentApplication)) {
                    Set<String> conditionKeys = new HashSet<String>();
                    conditionKeys.add(RegistryConstants.CATEGORY_KEY);
                    conditionKeys.add(com.xxy.rpc.remoting.Constants.CHECK_KEY);
                    conditionKeys.add(RegistryConstants.DYNAMIC_KEY);
                    conditionKeys.add(CommonConstants.ENABLED_KEY);
                    conditionKeys.add(CommonConstants.GROUP_KEY);
                    conditionKeys.add(CommonConstants.VERSION_KEY);
                    conditionKeys.add(CommonConstants.APPLICATION_KEY);
                    conditionKeys.add(CommonConstants.SIDE_KEY);
                    conditionKeys.add(Constants.CONFIG_VERSION_KEY);
                    conditionKeys.add(RegistryConstants.COMPATIBLE_CONFIG_KEY);
                    conditionKeys.add(CommonConstants.INTERFACES);
                    for (Map.Entry<String, String> entry : configuratorUrl.getParameters().entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (key.startsWith("~") || CommonConstants.APPLICATION_KEY.equals(key) || CommonConstants.SIDE_KEY.equals(key)) {
                            conditionKeys.add(key);
                            if (value != null && !CommonConstants.ANY_VALUE.equals(value)
                                    && !value.equals(url.getParameter(key.startsWith("~") ? key.substring(1) : key))) {
                                return url;
                            }
                        }
                    }
                    return doConfigure(url, configuratorUrl.removeParameters(conditionKeys));
                }
            }
        }
        return url;
    }

    protected abstract URL doConfigure(URL currentUrl, URL configUrl);

}
