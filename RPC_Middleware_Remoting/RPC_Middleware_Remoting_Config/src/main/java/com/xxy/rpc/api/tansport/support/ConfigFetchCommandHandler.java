package com.xxy.rpc.api.tansport.support;

import com.xxy.rpc.api.DataListener;
import com.xxy.rpc.api.EventType;
import com.xxy.rpc.api.request.CommandRequest;
import com.xxy.rpc.api.response.CommandResponse;
import com.xxy.rpc.api.tansport.CommandHandler;

/**
 * @Author: XXY
 * @Date: 2020/7/2 0:17
 */
public class ConfigFetchCommandHandler implements CommandHandler {
    private volatile DataListener dataListener;
    private static final String LISTENER_KEY = "config_change";
    public void addDataListener(DataListener dataListener){
        this.dataListener = dataListener;
    }
    @Override
    public CommandResponse handle(CommandRequest request) {
        dataListener.dataChanged(LISTENER_KEY, request.getBody(), EventType.UPDATE);
        return null;
    }
}
