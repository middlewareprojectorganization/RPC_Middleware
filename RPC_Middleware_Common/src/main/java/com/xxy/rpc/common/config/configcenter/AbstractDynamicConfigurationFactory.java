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
package com.xxy.rpc.common.config.configcenter;

import com.xxy.rpc.common.constants.CommonConstants;
import com.xxy.rpc.common.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Abstract {@link DynamicConfigurationFactory} implementation with cache ability
 *
 * @see DynamicConfigurationFactory
 * @since 2.7.5
 */
public abstract class AbstractDynamicConfigurationFactory implements DynamicConfigurationFactory {

    private volatile Map<String, DynamicConfiguration> dynamicConfigurations = new ConcurrentHashMap<>();

    @Override
    public final DynamicConfiguration getDynamicConfiguration(URL url) {
        String key = url == null ? CommonConstants.DEFAULT_KEY : url.getAddress();
        return dynamicConfigurations.computeIfAbsent(key, k -> createDynamicConfiguration(url));
    }

    protected abstract DynamicConfiguration createDynamicConfiguration(URL url);
}
