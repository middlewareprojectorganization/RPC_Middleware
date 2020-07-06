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
package com.xxy.rpc.api.tansport;


import com.xxy.rpc.common.utils.StringUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric Zhao
 */
@SuppressWarnings("rawtypes")
public final class HttpServer {

    private static final int DEFAULT_PORT = 8719;

    private Channel channel;

    public  Map<String, CommandHandler> getHandlerMap() {
        return handlerMap;
    }

    final static Map<String, CommandHandler> handlerMap = new ConcurrentHashMap<String, CommandHandler>();
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer());

            
            int retryCount = 0;
            ChannelFuture channelFuture = null;
            // loop for an successful binding
            while (true) {
                int newPort = getNewPort(DEFAULT_PORT, retryCount);
                try {
                    channelFuture = b.bind(newPort).sync();
                    LOGGER.info("[NettyHttpCommandCenter] Begin listening at port " + newPort);
                    break;
                } catch (Exception e) {
                    TimeUnit.MILLISECONDS.sleep(30);
                    LOGGER.warn("[HttpServer] Netty server bind error, port={}, retry={}", newPort, retryCount);
                    retryCount ++;
                }
            }
            channel = channelFuture.channel();
            channel.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    
    /**
     * Increase port number every 3 tries.
     * 
     * @param basePort base port to start
     * @param retryCount retry count
     * @return next calculated port
     */
    private int getNewPort(int basePort, int retryCount) {
        return basePort + retryCount / 3;
    }

    public void close() {
        channel.close();
    }

    public void registerCommand(String commandName, CommandHandler handler) {
        if (StringUtils.isEmpty(commandName) || handler == null) {
            return;
        }

        if (handlerMap.containsKey(commandName)) {
            LOGGER.warn("[NettyHttpCommandCenter] Register failed (duplicate command): " + commandName);
            return;
        }

        handlerMap.put(commandName, handler);
    }

    public void registerCommands(Map<String, CommandHandler> handlerMap) {
        if (handlerMap != null) {
            for (Entry<String, CommandHandler> e : handlerMap.entrySet()) {
                registerCommand(e.getKey(), e.getValue());
            }
        }
    }

}
