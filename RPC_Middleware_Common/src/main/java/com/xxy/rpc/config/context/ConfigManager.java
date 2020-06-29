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
package com.xxy.rpc.config.context;


import com.xxy.rpc.common.context.FrameworkExt;
import com.xxy.rpc.common.context.LifecycleAdapter;
import com.xxy.rpc.common.utils.CollectionUtils;
import com.xxy.rpc.common.utils.StringUtils;
import com.xxy.rpc.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import static com.xxy.rpc.common.constants.CommonConstants.DEFAULT_KEY;
import static com.xxy.rpc.common.utils.ReflectUtils.getProperty;
import static com.xxy.rpc.common.utils.StringUtils.isNotEmpty;
import static com.xxy.rpc.config.Constants.PROTOCOLS_SUFFIX;
import static com.xxy.rpc.config.Constants.REGISTRIES_SUFFIX;
import static java.lang.Boolean.TRUE;
import static java.util.Collections.emptyMap;
import static java.util.Collections.unmodifiableSet;
import static java.util.Optional.ofNullable;

public class ConfigManager extends LifecycleAdapter implements FrameworkExt {

    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    public static final String NAME = "config";

    private final Map<String, Map<String, AbstractConfig>> configsCache = newMap();
    private static final String[] SUFFIXES = new String[]{"Config", "Bean", "ConfigBase"};


    private final ReadWriteLock lock = new ReentrantReadWriteLock();


    private static Map newMap() {
        return new HashMap<>();
    }
    public ConfigManager() {
    }

    public void addConfig(AbstractConfig config) {
        if (config == null) {
            return;
        }
        Map<String, AbstractConfig> configsMap = configsCache.computeIfAbsent(getTagName(config.getClass()), type -> newMap());
        addIfAbsent(config, configsMap);
    }
    static <C extends AbstractConfig> void addIfAbsent(C config, Map<String, C> configsMap)
            throws IllegalStateException {

        if (config == null || configsMap == null) {
            return;
        }
        String key = getId(config);

        C existedConfig = configsMap.get(key);

        if (existedConfig != null && !config.equals(existedConfig)) {
            if (logger.isWarnEnabled()) {
                String type = config.getClass().getSimpleName();
                logger.warn(String.format("Duplicate %s found, there already has one default %s or more than two %ss have the same id, " +
                        "you can try to give each %s a different id : %s", type, type, type, type, config));
            }
        } else {
            configsMap.put(key, config);
        }
    }

    static <C extends AbstractConfig> String getId(C config) {
        String id = config.getId();
        return isNotEmpty(id) ? id : config.getClass().getSimpleName() + "#" + DEFAULT_KEY ;
    }


    public static String getTagName(Class<?> cls) {
        String tag = cls.getSimpleName();
        for (String suffix : SUFFIXES) {
            if (tag.endsWith(suffix)) {
                tag = tag.substring(0, tag.length() - suffix.length());
                break;
            }
        }
        return StringUtils.camelToSplitName(tag, "-");
    }
    public ConfigCenterConfig getConfigCenter(){
        return getConfig(getTagName(ConfigCenterConfig.class));
    }

    protected <C extends AbstractConfig> C getConfig(String configType) {
        return (C) configsCache.get(configType);
    }
}
