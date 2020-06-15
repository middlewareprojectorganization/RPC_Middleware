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
package com.xxy.rpc.config.metadata;

import com.xxy.rpc.common.URL;
import com.xxy.rpc.common.constants.CommonConstants;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.config.ApplicationConfig;
import com.xxy.rpc.config.ProtocolConfig;
import com.xxy.rpc.config.RegistryConfig;
import com.xxy.rpc.config.ServiceConfig;
import com.xxy.rpc.metadata.MetadataService;
import com.xxy.rpc.metadata.MetadataServiceExporter;
import com.xxy.rpc.rpc.model.ApplicationModel;
import com.xxy.rpc.config.context.ConfigManager;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * {@link MetadataServiceExporter} implementation based on {@link ConfigManager Dubbo configurations}, the clients
 * should make sure the {@link ApplicationConfig}, {@link RegistryConfig} and {@link ProtocolConfig} are ready before
 * {@link #export()}.
 * <p>
 * Typically, do not worry about their ready status, because they are initialized before
 * any {@link ServiceConfig} exports, or The Dubbo export will be failed.
 * <p>
 * Being aware of it's not a thread-safe implementation.
 *
 * @see MetadataServiceExporter
 * @see ServiceConfig
 * @see ConfigManager
 * @since 2.7.5
 */
public class ConfigurableMetadataServiceExporter implements MetadataServiceExporter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MetadataService metadataService;

    private volatile ServiceConfig<MetadataService> serviceConfig;

    public ConfigurableMetadataServiceExporter(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @Override
    public ConfigurableMetadataServiceExporter export() {

        if (!isExported()) {

            ServiceConfig<MetadataService> serviceConfig = new ServiceConfig<>();
            serviceConfig.setApplication(getApplicationConfig());
            serviceConfig.setRegistries(getRegistries());
            serviceConfig.setProtocol(generateMetadataProtocol());
            serviceConfig.setInterface(MetadataService.class);
            serviceConfig.setRef(metadataService);
            serviceConfig.setGroup(getApplicationConfig().getName());
            serviceConfig.setVersion(metadataService.version());

            // export
            serviceConfig.export();

            if (logger.isInfoEnabled()) {
                logger.info("The MetadataService exports urls : " + serviceConfig.getExportedUrls());
            }

            this.serviceConfig = serviceConfig;

        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("The MetadataService has been exported : " + serviceConfig.getExportedUrls());
            }
        }

        return this;
    }

    @Override
    public ConfigurableMetadataServiceExporter unexport() {
        if (isExported()) {
            serviceConfig.unexport();
        }
        return this;
    }

    @Override
    public List<URL> getExportedURLs() {
        return serviceConfig != null ? serviceConfig.getExportedUrls() : emptyList();
    }

    public boolean isExported() {
        return serviceConfig != null && serviceConfig.isExported();
    }

    private ApplicationConfig getApplicationConfig() {
        return ApplicationModel.getConfigManager().getApplication().get();
    }

    private List<RegistryConfig> getRegistries() {
        return new ArrayList<>(ApplicationModel.getConfigManager().getRegistries());
    }

    private ProtocolConfig generateMetadataProtocol() {
        ProtocolConfig defaultProtocol = new ProtocolConfig();
        defaultProtocol.setName(CommonConstants.DUBBO);
        // defaultProtocol.setHost() ?
        // auto-increment port
        defaultProtocol.setPort(-1);
        return defaultProtocol;
    }
}
