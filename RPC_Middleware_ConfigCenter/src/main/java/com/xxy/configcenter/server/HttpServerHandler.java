package com.xxy.configcenter.server;

import com.xxy.configcenter.HttpConfigClient;
import com.xxy.configcenter.Listener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: XXY
 * @Date: 2020/7/18 12:05
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        Listener listener = pickListener("");
        safeNotifyListener(listener, "");
    }

    private void safeNotifyListener(Listener listener , String content){
        listener.receiveConfigInfo(content);
    }
    private Listener pickListener(String key){
       return HttpConfigClient.listeners.get(key);
    }
}
