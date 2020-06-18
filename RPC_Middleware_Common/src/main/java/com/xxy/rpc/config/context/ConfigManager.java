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

    private final Map<String, Map<String, AbstractConfig>> configsCache = new ConcurrentHashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public ConfigManager() {
    }

}
