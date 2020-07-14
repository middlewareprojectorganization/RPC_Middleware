package com.xxy.rpc.impl;

import com.xxy.rpc.common.*;
import com.xxy.rpc.common.utils.NamedThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XXY
 * @Date: 2020/7/14 23:09
 */
public class HeartBeatSendFetchClientImpl implements HeartBeatFetchClient {
    private final URL url;
    private ScheduledExecutorService pool = null;
    private final DataListener dataListener;

    private void initSchedulerIfNeeded() {
        if (pool == null) {
            pool = new ScheduledThreadPoolExecutor(2,
                    new NamedThreadFactory("sentinel-heartbeat-send-task", true),
                    new ThreadPoolExecutor.DiscardOldestPolicy());
        }
    }

    public HeartBeatSendFetchClientImpl(URL url, DataListener dataListener){
        this.url = url;
        this.dataListener = dataListener;
    }
    @Override
    public void start() {
        ConfigFetchClient configFetchClient = new ConfigFetchClientImpl(url);
        initSchedulerIfNeeded();
        scheduleHeartbeatTask(configFetchClient, 1000);
    }

    private CommonResponse scheduleHeartbeatTask(/*@NonNull*/ final ConfigFetchClient sender, /*@Valid*/ long interval) {
        CommonResponse[] response = {null};
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    response[0] = sender.getConfig("");
                } catch (Throwable e) {

                }
            }
        }, 5000, interval, TimeUnit.MILLISECONDS);
        return response[0];
    }

    private void handlerDataChangeResponse(CommonResponse response){
        dataListener.dataChanged(null);
    }
}
