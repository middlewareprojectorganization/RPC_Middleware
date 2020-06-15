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
package com.xxy.rpc.registry.client.metadata.proxy;

import com.xxy.rpc.rpc.Protocol;
import com.xxy.rpc.rpc.ProxyFactory;

/**
 * Works on Consumer side, useful when using local metadata mode.
 *
 * Use this implementation to generate the proxy on Consumer side representing the remote MetadataService
 * exposed on the Provider side. Also see {@link RemoteMetadataServiceProxyFactory}
 *
 * @since 2.7.5
 */
public class DefaultMetadataServiceProxyFactory extends BaseMetadataServiceProxyFactory implements MetadataServiceProxyFactory {

    private ProxyFactory proxyFactory;

    private Protocol protocol;

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public void setProxyFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }




}
