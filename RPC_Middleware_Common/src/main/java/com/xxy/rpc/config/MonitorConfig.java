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

import com.xxy.rpc.common.constants.RegistryConstants;
import com.xxy.rpc.common.utils.StringUtils;

import java.util.Map;

/**
 * MonitorConfig
 *
 * @export
 */
public class MonitorConfig extends AbstractConfig {

    private static final long serialVersionUID = -1184681514659198203L;

    /**
     * The protocol of the monitor, if the value is registry, it will search the monitor address from the registry center,
     * otherwise, it will directly connect to the monitor center
     */
    private String protocol;

    /**
     * The monitor address
     */
    private String address;

    /**
     * The monitor user name
     */
    private String username;

    /**
     * The password
     */
    private String password;

    private String group;

    private String version;

    private String interval;

    /**
     * customized parameters
     */
    private Map<String, String> parameters;

    /**
     * If it's default
     */
    private Boolean isDefault;

    public MonitorConfig() {
    }


}
