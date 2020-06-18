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
package com.xxy.rpc.config;

import com.xxy.rpc.common.URL;

import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.config.bootstrap.RpcBootstrap;

import com.xxy.rpc.event.Event;
import com.xxy.rpc.rpc.Invoker;
import com.xxy.rpc.rpc.ProxyFactory;

import java.util.*;

import static com.xxy.rpc.common.constants.CommonConstants.*;
import static com.xxy.rpc.common.utils.NetUtils.isInvalidLocalHost;
import static com.xxy.rpc.config.Constants.DUBBO_IP_TO_REGISTRY;
import static com.xxy.rpc.rpc.Constants.LOCAL_PROTOCOL;

/**
 * Please avoid using this class for any new application,
 * use {@link ReferenceConfigBase} instead.
 */
public class ReferenceConfig<T> extends ReferenceConfigBase<T> {

    public static final Logger logger = LoggerFactory.getLogger(ReferenceConfig.class);

    /**
     * A {@link ProxyFactory} implementation that will generate a reference service's proxy,the JavassistProxyFactory is
     * its default implementation
     */
    private static final ProxyFactory PROXY_FACTORY = null;

    /**
     * The interface proxy reference
     */
    private transient volatile T ref;

    /**
     * The invoker of the reference service
     */
    private transient volatile Invoker<?> invoker;

    /**
     * The flag whether the ReferenceConfig has been initialized
     */
    private transient volatile boolean initialized;

    /**
     * whether this ReferenceConfig has been destroyed
     */
    private transient volatile boolean destroyed;

    private RpcBootstrap bootstrap;

    public ReferenceConfig() {
    }


    public synchronized T get() {
        if (destroyed) {
            throw new IllegalStateException("The invoker of ReferenceConfig(" + url + ") has already destroyed!");
        }
        if (ref == null) {
            init();
        }
        return ref;
    }

    public synchronized void destroy() {


    }

    public synchronized void init() {
    }

    @SuppressWarnings({"unchecked", "rawtypes", "deprecation"})
    private T createProxy(Map<String, String> map) {
        return null;
    }





    /**
     * Dispatch an {@link Event event}
     *
     * @since 2.7.5
     */

    public RpcBootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(RpcBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }


    public void appendParameters() {
    }

    // just for test
    Invoker<?> getInvoker() {
        return invoker;
    }
}
