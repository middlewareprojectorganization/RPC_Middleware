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
package com.xxy.rpc.rpc.cluster.directory;

import com.xxy.rpc.common.constants.CommonConstants;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.common.utils.StringUtils;
import com.xxy.rpc.common.utils.UrlUtils;
import com.xxy.rpc.rpc.Directory;
import com.xxy.rpc.rpc.Invocation;
import com.xxy.rpc.rpc.Invoker;
import com.xxy.rpc.rpc.RpcException;
import com.xxy.rpc.rpc.cluster.Constants;
import com.xxy.rpc.rpc.cluster.Router;
import com.xxy.rpc.rpc.cluster.RouterChain;
import com.xxy.rpc.common.URL;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Abstract implementation of Directory: Invoker list returned from this Directory's list method have been filtered by Routers
 *
 */
public abstract class AbstractDirectory<T> implements Directory<T> {

    // logger
    private static final Logger logger = LoggerFactory.getLogger(AbstractDirectory.class);

    private final URL url;

    private volatile boolean destroyed = false;

    private volatile URL consumerUrl;

    protected RouterChain<T> routerChain;

    public AbstractDirectory(URL url) {
        this(url, null);
    }

    public AbstractDirectory(URL url, RouterChain<T> routerChain) {
        this(url, url, routerChain);
    }

    public AbstractDirectory(URL url, URL consumerUrl, RouterChain<T> routerChain) {
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }

        if (UrlUtils.isRegistry(url)) {
            Map<String, String> queryMap = StringUtils.parseQueryString(url.getParameterAndDecoded(Constants.REFER_KEY));
            this.url = url.addParameters(queryMap).removeParameter(CommonConstants.MONITOR_KEY);
        } else {
            this.url = url;
        }

        this.consumerUrl = consumerUrl;
        setRouterChain(routerChain);
    }

    @Override
    public List<Invoker<T>> list(Invocation invocation) throws RpcException {
        if (destroyed) {
            throw new RpcException("Directory already destroyed .url: " + getUrl());
        }

        return doList(invocation);
    }

    @Override
    public URL getUrl() {
        return url;
    }

    public RouterChain<T> getRouterChain() {
        return routerChain;
    }

    public void setRouterChain(RouterChain<T> routerChain) {
        this.routerChain = routerChain;
    }

    protected void addRouters(List<Router> routers) {
        routers = routers == null ? Collections.emptyList() : routers;
        routerChain.addRouters(routers);
    }

    public URL getConsumerUrl() {
        return consumerUrl;
    }

    public void setConsumerUrl(URL consumerUrl) {
        this.consumerUrl = consumerUrl;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    protected abstract List<Invoker<T>> doList(Invocation invocation) throws RpcException;

}
