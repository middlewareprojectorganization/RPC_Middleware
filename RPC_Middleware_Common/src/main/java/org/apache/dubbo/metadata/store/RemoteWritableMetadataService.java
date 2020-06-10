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
package org.apache.dubbo.metadata.store;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.metadata.WritableMetadataService;

import org.apache.dubbo.metadata.report.MetadataReport;
import org.apache.dubbo.metadata.report.MetadataReportInstance;

import org.apache.dubbo.remoting.Constants;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.support.ProtocolUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.apache.dubbo.common.constants.CommonConstants.*;
import static org.apache.dubbo.rpc.Constants.GENERIC_KEY;

/**
 * The {@link WritableMetadataService} implementation stores the metadata of Dubbo services in metadata center when they
 * exported.
 * It is used by server (provider).
 *
 * @since 2.7.5
 */
public class RemoteWritableMetadataService implements WritableMetadataService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private volatile String exportedRevision;
    private volatile String subscribedRevision;
    private InMemoryWritableMetadataService writableMetadataService;

    public RemoteWritableMetadataService(InMemoryWritableMetadataService writableMetadataService) {
        this.writableMetadataService = writableMetadataService;
    }

    public MetadataReport getMetadataReport() {
        return MetadataReportInstance.getMetadataReport(true);
    }

    @Override
    public void publishServiceDefinition(URL providerUrl) {

    }

    @Deprecated
    public void publishProvider(URL providerUrl) throws RpcException {

    }

    @Deprecated
    public void publishConsumer(URL consumerURL) throws RpcException {
     }

    @Override
    public boolean exportURL(URL url) {
        return true;
    }

    @Override
    public boolean unexportURL(URL url) {
        return true;
    }

    @Override
    public boolean subscribeURL(URL url) {
        return true;
    }

    @Override
    public boolean unsubscribeURL(URL url) {
        return true;
    }

    @Override
    public boolean refreshMetadata(String exportedRevision, String subscribedRevision) {
   return  true;
     }

    private boolean saveServiceMetadata() {
        return  true;
     }


    @Override
    public SortedSet<String> getExportedURLs(String serviceInterface, String group, String version, String protocol) {
        return null;
    }

    @Override
    public String getServiceDefinition(String interfaceName, String version, String group) {
        return null;
    }

    @Override
    public String getServiceDefinition(String serviceKey) {
        return null;
    }





}
