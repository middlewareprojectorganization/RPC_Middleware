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

import com.xxy.rpc.config.AbstractConfig;
import com.xxy.rpc.spring.context.config.NamePropertyDefaultValueRpcConfigBeanCustomizer;
import com.xxy.rpc.spring.beans.factory.annotation.RpcConfigAliasPostProcessor;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import static com.alibaba.spring.util.AnnotatedBeanDefinitionRegistryUtils.registerBeans;
import static com.alibaba.spring.util.BeanRegistrar.registerInfrastructureBean;

/**
 * Dubbo {@link AbstractConfig Config} {@link ImportBeanDefinitionRegistrar register}, which order can be configured
 *
 * @see EnableRpcConfig
 * @see RpcConfigConfiguration
 * @see Ordered
 * @since 2.5.8
 */
public class RpcConfigConfigurationRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableRpcConfig.class.getName()));

        // Single Config Bindings
        registerBeans(registry, RpcConfigConfiguration.Single.class);

        // Register DubboConfigAliasPostProcessor
        registerRpcConfigAliasPostProcessor(registry);

        // Register NamePropertyDefaultValueDubboConfigBeanCustomizer
        registerRpcConfigBeanCustomizers(registry);

    }

    private void registerRpcConfigBeanCustomizers(BeanDefinitionRegistry registry) {
        registerInfrastructureBean(registry, NamePropertyDefaultValueRpcConfigBeanCustomizer.BEAN_NAME, NamePropertyDefaultValueRpcConfigBeanCustomizer.class);
    }

    /**
     * Register {@link RpcConfigAliasPostProcessor}
     *
     * @param registry {@link BeanDefinitionRegistry}
     * @since 2.7.4 [Feature] https://github.com/apache/dubbo/issues/5093
     */
    private void registerRpcConfigAliasPostProcessor(BeanDefinitionRegistry registry) {
        registerInfrastructureBean(registry, RpcConfigAliasPostProcessor.BEAN_NAME, RpcConfigAliasPostProcessor.class);
    }

}
