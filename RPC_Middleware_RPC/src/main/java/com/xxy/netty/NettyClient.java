package com.xxy.netty;

import com.xxy.URL;
import com.xxy.netty.codec.RpcDecoder;
import com.xxy.netty.codec.RpcEncoder;
import com.xxy.netty.handler.NettyClientHandler;
import com.xxy.request.RpcRequest;
import com.xxy.response.RpcResponse;
import com.xxy.netty.serializer.HessianSerializer;
import com.xxy.netty.serializer.Serializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author: XXY
 * @Date: 2020/7/31 1:16
 */
public class NettyClient extends AbstractClient{
    private static final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
    private Bootstrap clientBootStrap;
    public NettyClient(URL url) {
        super(url);
    }

    @Override
    public void connect() throws InterruptedException{
        ChannelFuture future = clientBootStrap.connect(getConnectAddress());
        if (future.isSuccess()) {
            System.out.println("连接服务器成功url：" + url.getHost() + "端口：" + url.getPort());
        }
        future.channel().closeFuture().sync();
    }

    public InetSocketAddress getConnectAddress() {
        return new InetSocketAddress(url.getHost(), url.getPort());
    }
    @Override
    public void doOpen() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        clientBootStrap = new Bootstrap();
        //childHandler针对服务端的workerHandler,而客户端不需要
        clientBootStrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                Serializer serializer = HessianSerializer.class.newInstance();
                ChannelPipeline pipeline = ch.pipeline();
                //将请求进行自定义编码实现protostuff序列化
                pipeline.addLast(new RpcEncoder(RpcRequest.class, serializer));
                pipeline.addLast(new RpcDecoder(RpcResponse.class, serializer));
                pipeline.addLast(new NettyClientHandler());
            }
        });

    }
}
