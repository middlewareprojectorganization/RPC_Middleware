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


import com.xxy.rpc.common.compiler.support.AdaptiveCompiler;

import com.xxy.rpc.common.infra.InfraAdapter;
import com.xxy.rpc.common.utils.CollectionUtils;
import com.xxy.rpc.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static com.xxy.rpc.common.constants.CommonConstants.*;
import static com.xxy.rpc.common.constants.QosConstants.*;
import static com.xxy.rpc.config.Constants.DEVELOPMENT_ENVIRONMENT;
import static com.xxy.rpc.config.Constants.PRODUCTION_ENVIRONMENT;
import static com.xxy.rpc.config.Constants.TEST_ENVIRONMENT;


/**
 * The application info
 *
 * @export
 */
public class ApplicationConfig extends AbstractConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    private static final long serialVersionUID = 5508512956753757169L;

    /**
     * Application name
     */
    private String name;

    /**
     * The application version
     */
    private String version;

    /**
     * Application owner
     */
    private String owner;

    /**
     * Application's organization (BU)
     */
    private String organization;

    /**
     * Architecture layer
     */
    private String architecture;

    /**
     * Environment, e.g. dev, test or production
     */
    private String environment;

    /**
     * Java compiler
     */
    private String compiler;

    /**
     * The type of the log access
     */
    private String logger;

    /**
     * Registry centers
     */
    private List<RegistryConfig> registries;
    private String registryIds;

    /**
     * Monitor center
     */
    private MonitorConfig monitor;

    /**
     * Is default or not
     */
    private Boolean isDefault;

    /**
     * Directory for saving thread dump
     */
    private String dumpDirectory;

    /**
     * Whether to enable qos or not
     */
    private Boolean qosEnable;

    /**
     * The qos host to listen
     */
    private String qosHost;

    /**
     * The qos port to listen
     */
    private Integer qosPort;

    /**
     * Should we accept foreign ip or not?
     */
    private Boolean qosAcceptForeignIp;

    /**
     * Customized parameters
     */
    private Map<String, String> parameters;

    /**
     * Config the shutdown.wait
     */
    private String shutwait;

    private String hostname;

    /**
     * Metadata type, local or remote, if choose remote, you need to further specify metadata center.
     */
    private String metadataType;

    private Boolean registerConsumer;

    private String repository;

    public ApplicationConfig() {
    }





}
