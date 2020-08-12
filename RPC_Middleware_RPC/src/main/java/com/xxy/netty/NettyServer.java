package com.xxy.netty;

import com.xxy.URL;
import com.xxy.netty.codec.RpcDecoder;
import com.xxy.netty.codec.RpcEncoder;
import com.xxy.request.RpcRequest;
import com.xxy.response.RpcResponse;
import com.xxy.netty.serializer.HessianSerializer;
import com.xxy.netty.serializer.Serializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XXY
 * @Date: 2020/8/3 22:59
 */
public class NettyServer {
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap bootstrap;
    private final URL url;
    private final List<ChannelHandler> handlerList;
    public NettyServer(URL url){
        this.url = url;
        handlerList = new ArrayList<>();
    }
    public void addHandler(ChannelHandler handler){
        handlerList.add(handler);
    }
    public void start(){
        bootstrap = new ServerBootstrap();
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        Serializer serializer = HessianSerializer.class.newInstance();
                        ChannelPipeline cp = nioSocketChannel.pipeline();
                        cp.addLast(new IdleStateHandler(0, 0, 5000, TimeUnit.SECONDS));
                        cp.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0));
                        cp.addLast(new RpcDecoder(RpcRequest.class, serializer));
                        cp.addLast(new RpcEncoder(RpcResponse.class, serializer));
                        handlerList.forEach(handler -> {
                            cp.addLast(handler);
                        });
                    }
                });
    }
}
