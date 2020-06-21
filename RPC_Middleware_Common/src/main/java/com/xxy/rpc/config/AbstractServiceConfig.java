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

import com.xxy.rpc.common.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xxy.rpc.common.constants.CommonConstants.EXPORTER_LISTENER_KEY;
import static com.xxy.rpc.common.constants.CommonConstants.SERVICE_FILTER_KEY;

/**
 * AbstractServiceConfig
 *
 * @export
 */
public abstract class AbstractServiceConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    /**
     * The service version
     */
    protected String version;

    /**
     * The service group
     */
    protected String group;

    /**
     * whether the service is deprecated
     */
    protected Boolean deprecated = false;

    /**
     * The time delay register service (milliseconds)
     */
    protected Integer delay;

    /**
     * Whether to export the service
     */
    protected Boolean export;

    /**
     * The service weight
     */
    protected Integer weight;

    /**
     * Document center
     */
    protected String document;

    /**
     * Whether to register as a dynamic service or not on register center, the value is true, the status will be enabled
     * after the service registered,and it needs to be disabled manually; if you want to disable the service, you also need
     * manual processing
     */
    protected Boolean dynamic = true;

    /**
     * Whether to use token
     */
    protected String token;

    /**
     * Whether to export access logs to logs
     */
    protected String accesslog;

    /**
     * The protocol list the service will export with
     */
    protected String protocolIds;

    // max allowed execute times
    private Integer executes;

    /**
     * Whether to register
     */
    private Boolean register;

    /**
     * Warm up period
     */
    private Integer warmup;

    /**
     * The serialization type
     */
    private String serialization;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Boolean getExport() {
        return export;
    }

    public void setExport(Boolean export) {
        this.export = export;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccesslog() {
        return accesslog;
    }

    public void setAccesslog(String accesslog) {
        this.accesslog = accesslog;
    }

    public String getProtocolIds() {
        return protocolIds;
    }

    public void setProtocolIds(String protocolIds) {
        this.protocolIds = protocolIds;
    }

    public Integer getExecutes() {
        return executes;
    }

    public void setExecutes(Integer executes) {
        this.executes = executes;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public Integer getWarmup() {
        return warmup;
    }

    public void setWarmup(Integer warmup) {
        this.warmup = warmup;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }
}