/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xxy.rpc.netty4;


import com.xxy.rpc.api.DataListener;
import com.xxy.rpc.api.NamedThreadFactory;
import com.xxy.rpc.api.tansport.CommandCenter;
import com.xxy.rpc.api.tansport.CommandHandler;
import com.xxy.rpc.api.tansport.HttpServer;
import com.xxy.rpc.api.tansport.config.ConfigClientConfig;
import com.xxy.rpc.api.tansport.support.ConfigFetchCommandHandler;
import com.xxy.rpc.config.ConfigCenterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Implementation of {@link CommandCenter} based on Netty HTTP library.
 *
 * @author Eric Zhao
 */
public class NettyHttpCommandCenter implements CommandCenter {

    private final HttpServer server = new HttpServer();
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyHttpCommandCenter.class);
    @SuppressWarnings("PMD.ThreadPoolCreationRule")
    private final ExecutorService pool = Executors.newSingleThreadExecutor(
        new NamedThreadFactory("netty-command-center-executor"));

    @Override
    public void beforeStart() throws Exception {
        Map<String, CommandHandler> handlers = new HashMap<>();
        handlers.put("config_change", new ConfigFetchCommandHandler());
        server.registerCommands(handlers);
    }

    @Override
    public void start() throws Exception {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    server.start();
                } catch (Exception ex) {
                    LOGGER.warn("[NettyHttpCommandCenter] Failed to start Netty transport server", ex);
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void stop() throws Exception {
        server.close();
        pool.shutdownNow();
    }

    @Override
    public void registerListener(DataListener dataListener) {
        ConfigFetchCommandHandler commandHandler = (ConfigFetchCommandHandler) server.getHandlerMap().get(ConfigClientConfig.CONFIG_CHANGE_HANDLER);
        commandHandler.addDataListener(dataListener);
    }

}
