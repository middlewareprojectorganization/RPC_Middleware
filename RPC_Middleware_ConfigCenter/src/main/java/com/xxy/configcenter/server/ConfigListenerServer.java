package com.xxy.configcenter.server;

import com.xxy.URL;
import com.xxy.configcenter.HttpServerHandler;
import com.xxy.configcenter.Listener;
import com.xxy.netty.NettyServer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;


public class ConfigListenerServer{

    /**
     * 开启配置变更监听
     * @throws Exception
     */
    private final URL url;
    private final NettyServer server;
    public ConfigListenerServer(URL url){
        this.url = url;
        server = new NettyServer(this.url);
        server.addHandler(new HttpServerHandler());
    }
    public void start() throws Exception{
           server.start();
        }
    }