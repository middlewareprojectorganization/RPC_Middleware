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
package com.xxy.rpc.metadata.store;

import com.xxy.rpc.common.URL;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.metadata.report.MetadataReport;
import com.xxy.rpc.metadata.report.MetadataReportInstance;
import com.xxy.rpc.rpc.RpcException;
import com.xxy.rpc.metadata.WritableMetadataService;

import java.util.SortedSet;

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
