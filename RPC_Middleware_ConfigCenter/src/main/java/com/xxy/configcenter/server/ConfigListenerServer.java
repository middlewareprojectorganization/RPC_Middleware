package com.xxy.configcenter.server;

import com.xxy.URL;
import com.xxy.configcenter.Listener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;


public class ConfigListenerServer{

    /**
     * 开启配置变更监听
     * @throws Exception
     */
    private final URL url;
    public ConfigListenerServer(URL url){
        this.url = url;
    }
    public void start() throws Exception{
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            EventLoopGroup workerGroup = new NioEventLoopGroup();

        }
    }