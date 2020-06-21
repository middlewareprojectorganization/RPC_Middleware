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
import com.xxy.rpc.common.constants.CommonConstants;
import com.xxy.rpc.common.utils.StringUtils;
import com.xxy.rpc.common.utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.xxy.rpc.common.constants.CommonConstants.*;
import static com.xxy.rpc.config.Constants.CONFIG_APP_CONFIGFILE_KEY;
import static com.xxy.rpc.config.Constants.ZOOKEEPER_PROTOCOL;


/**
 * ConfigCenterConfig
 */
public class ConfigCenterConfig extends AbstractConfig {
    private AtomicBoolean inited = new AtomicBoolean(false);
    private String address;
    private String username;
    private String password;
    private Long timeout = 3000L;
    private Map<String, String> parameters;

    private static ConfigCenterConfig configCenterConfig = new ConfigCenterConfig();

    public ConfigCenterConfig() {
    }

    public static ConfigCenterConfig getInstance(){
        return configCenterConfig;
    }
    public AtomicBoolean getInited() {
        return inited;
    }

    public void setInited(AtomicBoolean inited) {
        this.inited = inited;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }





}
