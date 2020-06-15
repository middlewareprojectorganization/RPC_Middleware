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
package com.xxy.rpc.config.event.listener;

import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.event.Event;
import com.xxy.rpc.event.GenericEventListener;
import com.xxy.rpc.config.event.DubboServiceDestroyedEvent;
import com.xxy.rpc.config.event.ServiceConfigExportedEvent;

import static java.lang.String.format;

/**
 * A listener for logging the {@link Event Dubbo event}
 *
 * @see ServiceConfigExportedEvent
 * @since 2.7.5
 */
public class LoggingEventListener extends GenericEventListener {

    private static final String NAME = "Dubbo Service";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void onEvent(DubboServiceDestroyedEvent event) {
        if (logger.isInfoEnabled()) {
            logger.info(NAME + " has been destroyed.");
        }
    }

    private void debug(String pattern, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(format(pattern, args));
        }
    }
}
