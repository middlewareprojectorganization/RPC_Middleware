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
package com.xxy.rpc.config;

import com.xxy.rpc.common.lang.ShutdownHookCallbacks;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.config.event.DubboServiceDestroyedEvent;
import com.xxy.rpc.config.event.DubboShutdownHookRegisteredEvent;
import com.xxy.rpc.config.event.DubboShutdownHookUnregisteredEvent;
import com.xxy.rpc.event.DirectEventDispatcher;
import com.xxy.rpc.event.Event;
import com.xxy.rpc.event.EventDispatcher;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The shutdown hook thread to do the clean up stuff.
 * This is a singleton in order to ensure there is only one shutdown hook registered.
 * Because {@link ApplicationShutdownHooks} use {@link java.util.IdentityHashMap}
 * to store the shutdown hooks.
 */
public class DubboShutdownHook extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(DubboShutdownHook.class);

    private static final DubboShutdownHook DUBBO_SHUTDOWN_HOOK = new DubboShutdownHook("DubboShutdownHook");

    private final ShutdownHookCallbacks callbacks = ShutdownHookCallbacks.INSTANCE;

    /**
     * Has it already been registered or not?
     */
    private final AtomicBoolean registered = new AtomicBoolean(false);

    /**
     * Has it already been destroyed or not?
     */
    private final AtomicBoolean destroyed = new AtomicBoolean(false);

    private final EventDispatcher eventDispatcher = new DirectEventDispatcher();

    private DubboShutdownHook(String name) {
        super(name);
    }

    public static DubboShutdownHook getDubboShutdownHook() {
        return DUBBO_SHUTDOWN_HOOK;
    }

    @Override
    public void run() {
        if (logger.isInfoEnabled()) {
            logger.info("Run shutdown hook now.");
        }
        callback();
        doDestroy();
    }

    /**
     * For testing purpose
     */
    void clear() {
        callbacks.clear();
    }

    private void callback() {
        callbacks.callback();
    }

    /**
     * Register the ShutdownHook
     */
    public void register() {
        if (!registered.get() && registered.compareAndSet(false, true)) {
            DubboShutdownHook dubboShutdownHook = getDubboShutdownHook();
            Runtime.getRuntime().addShutdownHook(dubboShutdownHook);
            dispatch(new DubboShutdownHookRegisteredEvent(dubboShutdownHook));
        }
    }

    /**
     * Unregister the ShutdownHook
     */
    public void unregister() {
        if (registered.get() && registered.compareAndSet(true, false)) {
            DubboShutdownHook dubboShutdownHook = getDubboShutdownHook();
            Runtime.getRuntime().removeShutdownHook(dubboShutdownHook);
            dispatch(new DubboShutdownHookUnregisteredEvent(dubboShutdownHook));
        }
    }

    /**
     * Destroy all the resources, including registries and protocols.
     */
    public void doDestroy() {
        if (!destroyed.compareAndSet(false, true)) {
            return;
        }

        // dispatch the DubboDestroyedEvent @since 2.7.5
        dispatch(new DubboServiceDestroyedEvent(this));
    }

    private void dispatch(Event event) {
        eventDispatcher.dispatch(event);
    }

}
