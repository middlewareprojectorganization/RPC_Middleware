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
package com.xxy.rpc.rpc.cluster.interceptor;

import com.xxy.rpc.common.constants.RegistryConstants;

import com.xxy.rpc.common.utils.StringUtils;
import com.xxy.rpc.rpc.Invocation;
import com.xxy.rpc.rpc.RpcContext;
import com.xxy.rpc.rpc.ZoneDetector;
import com.xxy.rpc.rpc.cluster.support.AbstractClusterInvoker;

/**
 * Determines the zone information of current request.
 *
 * active only when url has key 'cluster=zone-aware'
 */
public class ZoneAwareClusterInterceptor implements ClusterInterceptor {

    @Override
    public void before(AbstractClusterInvoker<?> clusterInvoker, Invocation invocation) {
        RpcContext rpcContext = RpcContext.getContext();
        String zone = (String) rpcContext.getAttachment(RegistryConstants.REGISTRY_ZONE);
        String force = (String) rpcContext.getAttachment(RegistryConstants.REGISTRY_ZONE_FORCE);

        if (StringUtils.isEmpty(zone) ) {
            ZoneDetector detector = null;
            zone = detector.getZoneOfCurrentRequest(invocation);
            force = detector.isZoneForcingEnabled(invocation, zone);
        }

        if (StringUtils.isNotEmpty(zone)) {
            invocation.setAttachment(RegistryConstants.REGISTRY_ZONE, zone);
        }
        if (StringUtils.isNotEmpty(force)) {
            invocation.setAttachment(RegistryConstants.REGISTRY_ZONE_FORCE, force);
        }
    }

    @Override
    public void after(AbstractClusterInvoker<?> clusterInvoker, Invocation invocation) {

    }
}
