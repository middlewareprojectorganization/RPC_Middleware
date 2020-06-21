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


import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;

import com.xxy.rpc.common.utils.CollectionUtils;
import com.xxy.rpc.config.*;




import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

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

    private List<CompletableFuture<Object>> asyncReferringFutures = new ArrayList<>();

    public static synchronized RpcBootstrap getInstance() {
        if (instance == null) {
            instance = new RpcBootstrap();
        }
        return instance;
    }

    private RpcBootstrap() {

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


    }



    /**
     * Start the bootstrap
     */
    public RpcBootstrap start() {
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