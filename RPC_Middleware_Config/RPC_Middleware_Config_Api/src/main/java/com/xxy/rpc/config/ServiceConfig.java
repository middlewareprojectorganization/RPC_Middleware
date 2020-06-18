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




import com.xxy.rpc.common.utils.*;
import com.xxy.rpc.config.bootstrap.RpcBootstrap;

import com.xxy.rpc.event.Event;

import com.xxy.rpc.rpc.*;

import javafx.event.EventDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;




public class ServiceConfig<T> extends ServiceConfigBase<T> {

    public static final Logger logger = LoggerFactory.getLogger(ServiceConfig.class);

    /**
     * A random port cache, the different protocols who has no port specified have different random port
     */
    private static final Map<String, Integer> RANDOM_PORT_MAP = new HashMap<String, Integer>();

    /**
     * A delayed exposure service timer
     */
    private static final ScheduledExecutorService DELAY_EXPORT_EXECUTOR = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("DubboServiceDelayExporter", true));

    private static final Protocol protocol = null;

    /**
     * A {@link ProxyFactory} implementation that will generate a exported service proxy,the JavassistProxyFactory is its
     * default implementation
     */
    private static final ProxyFactory PROXY_FACTORY = null;

    /**
     * Whether the provider has been exported
     */
    private transient volatile boolean exported;

    /**
     * The flag whether a service has unexported ,if the method unexported is invoked, the value is true
     */
    private transient volatile boolean unexported;

    private RpcBootstrap bootstrap;

    /**
     * The exported services
     */
    private final List<Exporter<?>> exporters = new ArrayList<Exporter<?>>();

    public ServiceConfig() {
    }


    public boolean isExported() {
        return exported;
    }

    public boolean isUnexported() {
        return unexported;
    }

    public void unexport() {


    }

    public synchronized void export() {

    }

    public RpcBootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(RpcBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }
}