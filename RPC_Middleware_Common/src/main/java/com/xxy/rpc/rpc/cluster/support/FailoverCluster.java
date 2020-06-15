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
package com.xxy.rpc.rpc.cluster.support;

import com.xxy.rpc.rpc.Directory;
import com.xxy.rpc.rpc.Invoker;
import com.xxy.rpc.rpc.RpcException;
import com.xxy.rpc.rpc.cluster.directory.StaticDirectory;
import com.xxy.rpc.rpc.cluster.support.wrapper.AbstractCluster;

/**
 * {@link FailoverClusterInvoker}
 *
 */
public class FailoverCluster extends AbstractCluster {

    public final static String NAME = "failover";


    @Override
    protected <T> AbstractClusterInvoker<T> doJoin(Directory<T> directory) throws RpcException {
        return null;
    }

    @Override
    public <T> Invoker<T> join(StaticDirectory directory) throws RpcException {
        return null;
    }
}
