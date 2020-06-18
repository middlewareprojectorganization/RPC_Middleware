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

    private String protocol;
    private String address;

    /* The config center cluster, it's real meaning may very on different Config Center products. */
    private String cluster;

    /* The namespace of the config center, generally it's used for multi-tenant,
    but it's real meaning depends on the actual Config Center you use.
    */

    private String namespace = CommonConstants.DUBBO;
    /* The group of the config center, generally it's used to identify an isolated space for a batch of config items,
    but it's real meaning depends on the actual Config Center you use.
    */
    private String group = CommonConstants.DUBBO;
    private String username;
    private String password;
    private Long timeout = 3000L;

    // If the Config Center is given the highest priority, it will override all the other configurations
    private Boolean highestPriority = true;

    // Decide the behaviour when initial connection try fails, 'true' means interrupt the whole process once fail.
    private Boolean check = true;

    /* Used to specify the key that your properties file mapping to, most of the time you do not need to change this parameter.
    Notice that for Apollo, this parameter is meaningless, set the 'namespace' is enough.
    */
    private String configFile = CommonConstants.DEFAULT_DUBBO_PROPERTIES;

    /* the .properties file under 'configFile' is global shared while .properties under this one is limited only to this application
    */
    private String appConfigFile;

    /* If the Config Center product you use have some special parameters that is not covered by this class, you can add it to here.
    For example, with XML:
      <dubbo:config-center>
           <dubbo:parameter key="{your key}" value="{your value}" />
      </dubbo:config-center>
     */
    private Map<String, String> parameters;

    private Map<String, String> externalConfiguration;

    private Map<String, String> appExternalConfiguration;

    public ConfigCenterConfig() {
    }


}
