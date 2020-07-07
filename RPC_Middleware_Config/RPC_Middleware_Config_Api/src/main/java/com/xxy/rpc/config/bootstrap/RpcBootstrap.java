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
package com.xxy.rpc.config.bootstrap;


import com.xxy.rpc.common.config.ConfigurationUtils;
import com.xxy.rpc.common.config.Environment;
import com.xxy.rpc.common.config.configcenter.DynamicConfiguration;
import com.xxy.rpc.common.config.configcenter.DynamicConfigurationFactory;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;

import com.xxy.rpc.common.utils.CollectionUtils;
import com.xxy.rpc.config.*;
import com.xxy.rpc.config.context.ConfigManager;
import com.xxy.rpc.configcenter.ConfigCenterDynamicConfigurationFactory;
import com.xxy.rpc.rpc.model.ApplicationModel;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.xxy.rpc.common.config.ConfigurationUtils.parseProperties;
import static java.util.Arrays.asList;

/**
 *
 * The bootstrap class of Dubbo
 *
 * Get singleton instance by calling static method {@link #getInstance()}.
 * Designed as singleton because some classes inside Dubbo, such as ExtensionLoader, are designed only for one instance per process.
 *
 * @since 2.7.5
 */
public class RpcBootstrap{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static RpcBootstrap instance;

    private AtomicBoolean initialized = new AtomicBoolean(false);

    private AtomicBoolean started = new AtomicBoolean(false);

    private List<Future<?>> asyncExportingFutures = new ArrayList<>();
    private Set<DynamicConfiguration> configurations = new HashSet<>();

    private List<CompletableFuture<Object>> asyncReferringFutures = new ArrayList<>();
    private final ConfigManager configManager;
    private final Environment environment;
    public static synchronized RpcBootstrap getInstance() {
        if (instance == null) {
            instance = new RpcBootstrap();
        }
        return instance;
    }

    private RpcBootstrap() {
        configManager = ApplicationModel.getConfigManager();
        environment = ApplicationModel.getEnvironment();
    }





    // {@link ConfigCenterConfig} correlative methods
    public RpcBootstrap configCenter(ConfigCenterConfig configCenterConfig) {
        return configCenters(asList(configCenterConfig));
    }

    public RpcBootstrap configCenters(List<ConfigCenterConfig> configCenterConfigs) {
        if (CollectionUtils.isEmpty(configCenterConfigs)) {
            return this;
        }
        return this;
    }


    /**
     * Initialize
     */
    private void initialize() {
        if (!initialized.compareAndSet(false, true)) {
            return;
        }
        //初始化环境
        ApplicationModel.iniFrameworkExts();
        //从配置中心拉取配置
        startConfigCenter();

    }

    private void loadRemoteConfig() {

    }

    private void startConfigCenter() {
        ConfigCenterConfig configCenter = configManager.getConfigCenter();
        if(configCenter != null){
            DynamicConfiguration dynamicConfiguration = prepareEnvironment(configCenter);
            configurations.add(dynamicConfiguration);
        }
    }

    private DynamicConfiguration prepareEnvironment(ConfigCenterConfig configCenter) {
        DynamicConfiguration dynamicConfiguration = new ConfigCenterDynamicConfigurationFactory().
                getDynamicConfiguration(configCenter.toUrl());
        String content = dynamicConfiguration.getConfig();
        try {
            environment.updateAppExternalConfigurationMap(parseProperties(content));
        }catch (IOException e){
            throw new IllegalStateException("Failed to parse configurations from Config Center.", e);
        }
        return dynamicConfiguration;

    }


    /**
     * Start the bootstrap
     */
    public RpcBootstrap start() {
        //拉取配置
        
        return this;
    }


    /**
     * Block current thread to be await.
     *
     * @return {@link RpcBootstrap}
     */
    public RpcBootstrap await() {

        return this;
    }

    public RpcBootstrap awaitFinish() throws Exception {
        return this;
    }

    public boolean isInitialized() {
        return initialized.get();
    }

    public boolean isStarted() {
        return started.get();
    }

    public RpcBootstrap stop() throws IllegalStateException {
        destroy();
        return this;
    }


    public void destroy() {

    }







}
