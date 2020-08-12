package com.xxy.netty.handler;

import com.xxy.request.RpcRequest;
import io.netty.channel.*;

/**
 * @Author: XXY
 * @Date: 2020/7/31 22:27
 */
@ChannelHandler.Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler<RpcRequest> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(null);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest) throws Exception {

    }


}
