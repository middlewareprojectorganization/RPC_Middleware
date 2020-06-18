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
package com.xxy.rpc.spring.context.annotation;

import com.xxy.rpc.config.*;
import com.xxy.rpc.config.AbstractConfig;
import com.xxy.rpc.config.ApplicationConfig;
import com.xxy.rpc.config.ConsumerConfig;
import com.xxy.rpc.config.MetadataReportConfig;
import com.xxy.rpc.config.MetricsConfig;
import com.xxy.rpc.config.ModuleConfig;
import com.xxy.rpc.config.MonitorConfig;
import com.xxy.rpc.config.ProtocolConfig;
import com.xxy.rpc.config.ProviderConfig;
import com.xxy.rpc.config.RegistryConfig;
import com.xxy.rpc.config.SslConfig;
import com.xxy.rpc.spring.ConfigCenterBean;

import com.alibaba.spring.beans.factory.annotation.EnableConfigurationBeanBinding;
import com.alibaba.spring.beans.factory.annotation.EnableConfigurationBeanBindings;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo {@link AbstractConfig Config} {@link Configuration}
 *
 * @revised 2.7.5
 * @see Configuration
 * @see EnableConfigurationBeanBindings
 * @see EnableConfigurationBeanBinding
 * @see ApplicationConfig
 * @see ModuleConfig
 * @see RegistryConfig
 * @see ProtocolConfig
 * @see MonitorConfig
 * @see ProviderConfig
 * @see ConsumerConfig
 * @see ConfigCenterConfig
 * @since 2.5.8
 */
public class RpcConfigConfiguration {

    /**
     * Single Dubbo {@link AbstractConfig Config} Bean Binding
     */
    @EnableConfigurationBeanBindings({
            @EnableConfigurationBeanBinding(prefix = "rpc.application", type = ApplicationConfig.class),
            @EnableConfigurationBeanBinding(prefix = "rpc.registry", type = RegistryConfig.class),
            @EnableConfigurationBeanBinding(prefix = "rpc.monitor", type = MonitorConfig.class),
            @EnableConfigurationBeanBinding(prefix = "rpc.provider", type = ProviderConfig.class),
            @EnableConfigurationBeanBinding(prefix = "rpc.consumer", type = ConsumerConfig.class),
            @EnableConfigurationBeanBinding(prefix = "rpc.config-center", type = ConfigCenterBean.class),
            @EnableConfigurationBeanBinding(prefix = "rpc.metrics", type = MetricsConfig.class)
    })
    public static class Single {

    }


}
