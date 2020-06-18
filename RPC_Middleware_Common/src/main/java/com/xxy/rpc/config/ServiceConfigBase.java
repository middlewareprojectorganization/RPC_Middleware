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
import com.xxy.rpc.common.utils.CollectionUtils;
import com.xxy.rpc.common.utils.StringUtils;
import com.xxy.rpc.config.annotation.RpcService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.xxy.rpc.common.constants.CommonConstants.COMMA_SPLIT_PATTERN;
import static com.xxy.rpc.common.constants.CommonConstants.DUBBO;


/**
 * ServiceConfig
 *
 * @export
 */
public abstract class ServiceConfigBase<T> extends AbstractServiceConfig {

    private static final long serialVersionUID = 3033787999037024738L;

    /**
     * The interface name of the exported service
     */
    protected String interfaceName;

    /**
     * The interface class of the exported service
     */
    protected Class<?> interfaceClass;

    /**
     * The reference of the interface implementation
     */
    protected T ref;

    /**
     * The service name
     */
    protected String path;

    /**
     * The provider configuration
     */
    protected ProviderConfig provider;

    /**
     * The providerIds
     */
    protected String providerIds;

    /**
     * whether it is a GenericService
     */
    protected volatile String generic;



    public abstract void export();

    public abstract void unexport();

    public abstract boolean isExported();

    public abstract boolean isUnexported();

}