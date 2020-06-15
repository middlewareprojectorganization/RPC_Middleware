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
package com.xxy.rpc.config.event;

import com.xxy.rpc.config.ReferenceConfig;
import com.xxy.rpc.event.Event;
import com.xxy.rpc.rpc.Invoker;

/**
 * The {@link ReferenceConfig Dubbo service ReferenceConfig} initialized {@link Event event}
 *
 * @see ReferenceConfig#get()
 * @see Event
 * @since 2.7.5
 */
public class ReferenceConfigInitializedEvent extends Event {

    private final Invoker<?> invoker;

    public ReferenceConfigInitializedEvent(ReferenceConfig referenceConfig, Invoker<?> invoker) {
        super(referenceConfig);
        this.invoker = invoker;
    }

    public ReferenceConfig getReferenceConfig() {
        return (ReferenceConfig) getSource();
    }

    public Invoker<?> getInvoker() {
        return invoker;
    }
}
