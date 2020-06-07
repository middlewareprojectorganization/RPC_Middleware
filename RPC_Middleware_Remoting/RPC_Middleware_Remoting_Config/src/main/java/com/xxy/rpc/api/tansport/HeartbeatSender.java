package com.xxy.rpc.api.tansport;

/**
 * @Author: XXY
 * @Date: 2020/6/3 22:52
 */
public interface HeartbeatSender {
    /**
     * Send heartbeat to Sentinel Dashboard. Each invocation of this method will send
     * heartbeat once. Sentinel core is responsible for invoking this method
     * at every {@link #intervalMs()} interval.
     *
     * @return whether heartbeat is successfully send.
     * @throws Exception if error occurs
     */
    boolean sendHeartbeat(String consoleHost) throws Exception;

    /**
     * Default interval in milliseconds of the sender. It would take effect only when
     * the heartbeat interval is not configured in Sentinel config property.
     *
     * @return default interval of the sender in milliseconds
     */
    long intervalMs();
}
