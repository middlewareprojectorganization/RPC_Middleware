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

import com.xxy.rpc.common.constants.CommonConstants;
import com.xxy.rpc.common.URL;
import com.xxy.rpc.remoting.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * ClusterUtils
 */
public class ClusterUtils {

    private ClusterUtils() {
    }

    public static URL mergeUrl(URL remoteUrl, Map<String, String> localMap) {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> remoteMap = remoteUrl.getParameters();

        if (remoteMap != null && remoteMap.size() > 0) {
            map.putAll(remoteMap);

            // Remove configurations from provider, some items should be affected by provider.
            map.remove(CommonConstants.THREAD_NAME_KEY);
            map.remove(CommonConstants.DEFAULT_KEY_PREFIX + CommonConstants.THREAD_NAME_KEY);

            map.remove(CommonConstants.THREADPOOL_KEY);
            map.remove(CommonConstants.DEFAULT_KEY_PREFIX + CommonConstants.THREADPOOL_KEY);

            map.remove(CommonConstants.CORE_THREADS_KEY);
            map.remove(CommonConstants.DEFAULT_KEY_PREFIX + CommonConstants.CORE_THREADS_KEY);

            map.remove(CommonConstants.THREADS_KEY);
            map.remove(CommonConstants.DEFAULT_KEY_PREFIX + CommonConstants.THREADS_KEY);

            map.remove(CommonConstants.QUEUES_KEY);
            map.remove(CommonConstants.DEFAULT_KEY_PREFIX + CommonConstants.QUEUES_KEY);

            map.remove(CommonConstants.ALIVE_KEY);
            map.remove(CommonConstants.DEFAULT_KEY_PREFIX + CommonConstants.ALIVE_KEY);

            map.remove(Constants.TRANSPORTER_KEY);
            map.remove(CommonConstants.DEFAULT_KEY_PREFIX + Constants.TRANSPORTER_KEY);
        }

        if (localMap != null && localMap.size() > 0) {
            Map<String, String> copyOfLocalMap = new HashMap<>(localMap);

            if(map.containsKey(CommonConstants.GROUP_KEY)){
                copyOfLocalMap.remove(CommonConstants.GROUP_KEY);
            }
            if(map.containsKey(CommonConstants.VERSION_KEY)){
                copyOfLocalMap.remove(CommonConstants.VERSION_KEY);
            }

            copyOfLocalMap.remove(CommonConstants.RELEASE_KEY);
            copyOfLocalMap.remove(CommonConstants.DUBBO_VERSION_KEY);
            copyOfLocalMap.remove(CommonConstants.METHODS_KEY);
            copyOfLocalMap.remove(CommonConstants.TIMESTAMP_KEY);
            copyOfLocalMap.remove(CommonConstants.TAG_KEY);

            map.putAll(copyOfLocalMap);

            map.put(CommonConstants.REMOTE_APPLICATION_KEY, remoteMap.get(CommonConstants.APPLICATION_KEY));

            // Combine filters and listeners on Provider and Consumer
            String remoteFilter = remoteMap.get(CommonConstants.REFERENCE_FILTER_KEY);
            String localFilter = copyOfLocalMap.get(CommonConstants.REFERENCE_FILTER_KEY);
            if (remoteFilter != null && remoteFilter.length() > 0
                    && localFilter != null && localFilter.length() > 0) {
                map.put(CommonConstants.REFERENCE_FILTER_KEY, remoteFilter + "," + localFilter);
            }
            String remoteListener = remoteMap.get(CommonConstants.INVOKER_LISTENER_KEY);
            String localListener = copyOfLocalMap.get(CommonConstants.INVOKER_LISTENER_KEY);
            if (remoteListener != null && remoteListener.length() > 0
                    && localListener != null && localListener.length() > 0) {
                map.put(CommonConstants.INVOKER_LISTENER_KEY, remoteListener + "," + localListener);
            }
        }

        return remoteUrl.clearParameters().addParameters(map);
    }

}
