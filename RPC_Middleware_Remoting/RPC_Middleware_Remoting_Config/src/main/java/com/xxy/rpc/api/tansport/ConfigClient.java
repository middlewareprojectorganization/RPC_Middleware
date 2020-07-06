package com.xxy.rpc.api.tansport;

import com.xxy.rpc.api.DataListener;
import com.xxy.rpc.api.response.CommandResponse;

/**
 * @Author: XXY
 * @Date: 2020/6/3 22:52
 */
public interface ConfigClient {
    /**
     * Send heartbeat to Sentinel Dashboard. Each invocation of this method will send
     * heartbeat once. Sentinel core is responsible for invoking this method
     * at every {@link #intervalMs()} interval.
     *
     * @return whether heartbeat is successfully send.
     * @throws Exception if error occurs
     */
    CommandResponse getConfig() throws Exception;

    void start() ;
    void stop();
    void addListener(DataListener dataListener);
}
