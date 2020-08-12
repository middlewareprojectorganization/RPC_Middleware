package com.xxy.rpc.cient;

import io.netty.channel.ChannelHandlerContext;

public interface NettyClientListener {
    void channelRead(ChannelHandlerContext ctx, Object message);
}
