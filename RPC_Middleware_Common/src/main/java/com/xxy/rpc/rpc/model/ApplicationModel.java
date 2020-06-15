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
package com.xxy.rpc.rpc.model;

import com.xxy.rpc.common.config.Environment;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.config.ApplicationConfig;
import com.xxy.rpc.config.context.ConfigManager;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;


public class ApplicationModel {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ApplicationModel.class);
    public static final String NAME = "application";

    private static AtomicBoolean INIT_FLAG = new AtomicBoolean(false);



    public static Collection<ConsumerModel> allConsumerModels() {
        return getServiceRepository().getReferredServices();
    }

    public static Collection<ProviderModel> allProviderModels() {
        return getServiceRepository().getExportedServices();
    }

    public static ProviderModel getProviderModel(String serviceKey) {
        return getServiceRepository().lookupExportedService(serviceKey);
    }

    public static ConsumerModel getConsumerModel(String serviceKey) {
        return getServiceRepository().lookupReferredService(serviceKey);
    }


    public static void iniFrameworkExts() {
       getEnvironment().initialize();
    }

    public static Environment getEnvironment() {
        return new Environment();
    }

    public static ConfigManager getConfigManager() {
        return new ConfigManager();
    }

    public static ServiceRepository getServiceRepository() {
        return new ServiceRepository();
    }

    public static ApplicationConfig getApplicationConfig() {
        return getConfigManager().getApplicationOrElseThrow();
    }

    public static String getName() {
        return getApplicationConfig().getName();
    }

    @Deprecated
    private static String application;

    @Deprecated
    public static String getApplication() {
        return application == null ? getName() : application;
    }

    // Currently used by UT.
    @Deprecated
    public static void setApplication(String application) {
        ApplicationModel.application = application;
    }

    // only for unit test
    public static void reset() {
        getServiceRepository().destroy();
        getConfigManager().destroy();
        getEnvironment().destroy();
    }

}
