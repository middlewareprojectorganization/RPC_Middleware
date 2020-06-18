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

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enables Dubbo components as Spring Beans, equals
 * {@link RpcComponentScan} and {@link EnableRpcConfig} combination.
 * <p>
 *
 * @see RpcComponentScan
 * @see EnableRpcConfig
 * @since 2.5.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@EnableRpcConfig
@RpcComponentScan
@EnableDubboLifecycle
public @interface EnableRpc{

    /**
     * Base packages to scan for annotated @Service classes.
     * <p>
     * Use {@link #scanBasePackageClasses()} for a type-safe alternative to String-based
     * package names.
     *
     * @return the base packages to scan
     * @see RpcComponentScan#basePackages()
     */
    @AliasFor(annotation = RpcComponentScan.class, attribute = "basePackages")
    String[] scanBasePackages() default {};

    /**
     * Type-safe alternative to {@link #scanBasePackages()} for specifying the packages to
     * scan for annotated @Service classes. The package of each class specified will be
     * scanned.
     *
     * @return classes from the base packages to scan
     * @see RpcComponentScan#basePackageClasses
     */
    @AliasFor(annotation = RpcComponentScan.class, attribute = "basePackageClasses")
    Class<?>[] scanBasePackageClasses() default {};


    /**
     * It indicates whether {@link AbstractConfig} binding to multiple Spring Beans.
     *
     * @return the default value is <code>false</code>
     * @see EnableRpcConfig#multiple()
     */
    @AliasFor(annotation = EnableRpcConfig.class, attribute = "multiple")
    boolean multipleConfig() default true;

}
