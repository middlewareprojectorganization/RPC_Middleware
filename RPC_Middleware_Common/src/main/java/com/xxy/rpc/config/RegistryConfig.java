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

import java.util.Map;

import static com.xxy.rpc.common.constants.CommonConstants.EXTRA_KEYS_KEY;
import static com.xxy.rpc.common.constants.CommonConstants.SHUTDOWN_WAIT_KEY;
import static com.xxy.rpc.config.Constants.REGISTRIES_SUFFIX;

/**
 * RegistryConfig
 *
 * @export
 */
public class RegistryConfig extends AbstractConfig {

    public static final String NO_AVAILABLE = "N/A";
    private static final long serialVersionUID = 5508512956753757169L;

    /**
     * Register center address
     */
    private String address;

    /**
     * Username to login register center
     */
    private String username;

    /**
     * Password to login register center
     */
    private String password;

    /**
     * Default port for register center
     */
    private Integer port;

    /**
     * Protocol for register center
     */

    /**
     * Network transmission type
     */
    private String transporter;

    private String server;

    private String client;










    /**
     * Session timeout in milliseconds for register center
     */
    private Integer session;

    /**
     * File for saving register center dynamic list
     */
    private String file;

    /**
     * Wait time before stop
     */
    private Integer wait;

    /**
     * Whether to check if register center is available when boot up
     */
    private Boolean check;

    /**
     * Whether to allow dynamic service to register on the register center
     */
    private Boolean dynamic;

    /**
     * Whether to export service on the register center
     */
    private Boolean register;

    /**
     * Whether allow to subscribe service on the register center
     */
    private Boolean subscribe;

    /**
     * The customized parameters
     */
    private Map<String, String> parameters;

    /**
     * Whether it's default
     */
    private Boolean isDefault;

    /**
     * Simple the registry. both useful for provider and consumer
     *
     * @since 2.7.0
     */
    private Boolean simplified;
    /**
     * After simplify the registry, should add some paramter individually. just for provider.
     * <p>
     * such as: extra-keys = A,b,c,d
     *
     * @since 2.7.0
     */
    private String extraKeys;

    /**
     * the address work as config center or not
     */
    private Boolean useAsConfigCenter;

    /**
     * the address work as remote metadata center or not
     */
    private Boolean useAsMetadataCenter;

    /**
     * list of rpc protocols accepted by this registry, for example, "dubbo,rest"
     */
    private String accepts;

    /**
     * Always use this registry first if set to true, useful when subscribe to multiple registries
     */
    private Boolean preferred;

    /**
     * Affects traffic distribution among registries, useful when subscribe to multiple registries
     * Take effect only when no preferred registry is specified.
     */
    private Integer weight;

    public RegistryConfig() {
    }



}
