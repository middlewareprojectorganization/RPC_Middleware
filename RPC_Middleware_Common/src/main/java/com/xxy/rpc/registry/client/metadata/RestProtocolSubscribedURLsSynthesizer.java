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
package com.xxy.rpc.registry.client.metadata;

import com.xxy.rpc.common.URL;
import com.xxy.rpc.common.constants.CommonConstants;
import com.xxy.rpc.rpc.Protocol;
import com.xxy.rpc.common.URLBuilder;
import com.xxy.rpc.registry.client.ServiceInstance;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;
import static com.xxy.rpc.registry.Constants.REGISTER_KEY;

/**
 * {@link SubscribedURLsSynthesizer} implementation for REST {@link Protocol protocol}
 *
 * @since 2.7.5
 */
public class RestProtocolSubscribedURLsSynthesizer implements SubscribedURLsSynthesizer {

    @Override
    public boolean supports(URL subscribedURL) {
        return "rest".equals(subscribedURL.getProtocol()) ||
                "rest".equals(subscribedURL.getParameter(CommonConstants.PROTOCOL_KEY));
    }

    @Override
    public List<URL> synthesize(URL subscribedURL, Collection<ServiceInstance> serviceInstances) {

        String protocol = subscribedURL.getParameter(CommonConstants.PROTOCOL_KEY);

        return serviceInstances.stream().map(serviceInstance -> {
            URLBuilder urlBuilder = new URLBuilder()
                    .setProtocol(protocol)
                    .setHost(serviceInstance.getHost())
                    .setPort(serviceInstance.getPort())
                    .setPath(subscribedURL.getServiceInterface())
                    .addParameter(CommonConstants.SIDE_KEY, CommonConstants.PROVIDER)
                    .addParameter(CommonConstants.APPLICATION_KEY, serviceInstance.getServiceName())
                    .addParameter(REGISTER_KEY, TRUE.toString());

            return urlBuilder.build();
        }).collect(Collectors.toList());
    }

}
