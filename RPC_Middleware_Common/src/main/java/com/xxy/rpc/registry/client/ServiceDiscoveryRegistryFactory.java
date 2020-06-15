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
package com.xxy.rpc.registry.client;

import com.xxy.rpc.common.URL;
import com.xxy.rpc.common.constants.RegistryConstants;
import com.xxy.rpc.registry.support.AbstractRegistryFactory;
import com.xxy.rpc.registry.Registry;

import static com.xxy.rpc.registry.Constants.DEFAULT_REGISTRY;

public class ServiceDiscoveryRegistryFactory extends AbstractRegistryFactory {

    @Override
    protected Registry createRegistry(URL url) {
        if (RegistryConstants.SERVICE_REGISTRY_PROTOCOL.equalsIgnoreCase(url.getProtocol())) {
            String protocol = url.getParameter(RegistryConstants.REGISTRY_KEY, DEFAULT_REGISTRY);
            url = url.setProtocol(protocol).removeParameter(RegistryConstants.REGISTRY_KEY);
        }
        return new ServiceDiscoveryRegistry(url);
    }

}
