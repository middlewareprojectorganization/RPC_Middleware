package com.xxy.netty;

import com.xxy.URL;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

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
    public void connect() {
        clientBootStrap = new Bootstrap();
        clientBootStrap.group(nioEventLoopGroup)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                //.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, getTimeout())
                .channel(NioSocketChannel.class);

    }

    @Override
    public void doOpen() {

    }
}
