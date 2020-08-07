package com.xxy.rpc.server.protocal.imply;

import com.xxy.URL;
import com.xxy.entity.Invocation;
import com.xxy.rpc.cient.NettyClient;
import com.xxy.rpc.server.protocal.Protocal;

public class NettyProtocal implements Protocal {
    @Override
    public Object invokeProtocl(URL url, Invocation invocation) {
        NettyClient nettyClient=new NettyClient();
        return nettyClient.send(url.getHost(),url.getPort(),invocation);
    }

    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHost(),url.getPort());

    }
}
