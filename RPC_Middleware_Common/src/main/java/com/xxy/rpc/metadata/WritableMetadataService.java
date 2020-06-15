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
package com.xxy.rpc.metadata;

import com.xxy.rpc.common.URL;
import com.xxy.rpc.common.constants.CommonConstants;


import com.xxy.rpc.metadata.store.InMemoryWritableMetadataService;
import com.xxy.rpc.metadata.store.RemoteWritableMetadataService;
import com.xxy.rpc.rpc.model.ApplicationModel;

/**
 * Local {@link MetadataService} that extends {@link MetadataService} and provides the modification, which is used for
 * Dubbo's consumers and providers.
 *
 * @since 2.7.5
 */
public interface WritableMetadataService extends MetadataService {
    /**
     * Gets the current Dubbo Service name
     *
     * @return non-null
     */
    @Override
    default String serviceName() {
        return ApplicationModel.getApplication();
    }

    /**
     * Exports a {@link URL}
     *
     * @param url a {@link URL}
     * @return If success , return <code>true</code>
     */
    boolean exportURL(URL url);

    /**
     * Unexports a {@link URL}
     *
     * @param url a {@link URL}
     * @return If success , return <code>true</code>
     */
    boolean unexportURL(URL url);

    /**
     * fresh Exports
     *
     * @return If success , return <code>true</code>
     */
    default boolean refreshMetadata(String exportedRevision, String subscribedRevision) {
        return true;
    }

    /**
     * Subscribes a {@link URL}
     *
     * @param url a {@link URL}
     * @return If success , return <code>true</code>
     */
    boolean subscribeURL(URL url);

    /**
     * Unsubscribes a {@link URL}
     *
     * @param url a {@link URL}
     * @return If success , return <code>true</code>
     */
    boolean unsubscribeURL(URL url);

    void publishServiceDefinition(URL providerUrl);

    /**
     *
     * @return non-null
     * @see InMemoryWritableMetadataService
     */
    static WritableMetadataService getDefaultExtension() {
        return new InMemoryWritableMetadataService();
    }

    static WritableMetadataService getExtension(String name) {
        return new InMemoryWritableMetadataService();
    }
}
