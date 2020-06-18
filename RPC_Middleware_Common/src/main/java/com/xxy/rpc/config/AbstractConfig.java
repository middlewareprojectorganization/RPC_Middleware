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
import com.xxy.rpc.common.config.CompositeConfiguration;
import com.xxy.rpc.common.config.Configuration;
import com.xxy.rpc.common.config.Environment;
import com.xxy.rpc.common.constants.CommonConstants;
import com.xxy.rpc.common.utils.*;
import com.xxy.rpc.config.context.ConfigManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;



/**
 * Utility methods and public methods for parsing configuration
 *
 * @export
 */
public abstract class AbstractConfig implements Serializable {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractConfig.class);
    private static final long serialVersionUID = 4267533505537413570L;






    public static void appendParameters(Map<String, String> parameters, Object config) {
        appendParameters(parameters, config, null);
    }

    @SuppressWarnings("unchecked")
    public static void appendParameters(Map<String, String> parameters, Object config, String prefix) {

    }

    @Deprecated
    protected static void appendAttributes(Map<String, Object> parameters, Object config) {
        appendAttributes(parameters, config, null);
    }

    @Deprecated
    protected static void appendAttributes(Map<String, Object> parameters, Object config, String prefix) {

    }






    public void refresh() {

    }

    @Override
    public String toString() {
        return "";
    }


}
