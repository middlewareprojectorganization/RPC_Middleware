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
import com.xxy.rpc.common.utils.ClassUtils;
import com.xxy.rpc.common.utils.StringUtils;
import com.xxy.rpc.config.annotation.RpcReference;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.xxy.rpc.common.constants.CommonConstants.DUBBO;

/**
 * ReferenceConfig
 *
 * @export
 */
public abstract class ReferenceConfigBase<T> extends AbstractReferenceConfig {

    private static final long serialVersionUID = -5864351140409987595L;

    /**
     * The interface name of the reference service
     */
    protected String interfaceName;

    /**
     * The interface class of the reference service
     */
    protected Class<?> interfaceClass;

    /**
     * client type
     */
    protected String client;

    /**
     * The url for peer-to-peer invocation
     */
    protected String url;

    /**
     * The consumer config (default)
     */
    protected ConsumerConfig consumer;

    /**
     * Only the service provider of the specified protocol is invoked, and other protocols are ignored.
     */
    protected String protocol;


    public abstract T get();

    public abstract void destroy();


}
