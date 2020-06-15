package com.xxy.rpc;

import com.xxy.rpc.config.ConfigCenterConfig;
import com.xxy.rpc.config.bootstrap.DubboBootstrap;
import org.junit.Test;

/**
 * @Author: XXY
 * @Date: 2020/6/14 18:40
 */
public class ApplicationTest {

    @Test
    public void testStart(){
        DubboBootstrap dubboBootstrap = DubboBootstrap.getInstance();
        ConfigCenterConfig configCenterConfig = new ConfigCenterConfig();
        configCenterConfig.setAddress("127.0.0.1:8080");
        configCenterConfig.setProtocol("config-center");
        configCenterConfig.setNamespace(null);
        configCenterConfig.setGroup(null);
        dubboBootstrap.configCenter(configCenterConfig);
        dubboBootstrap.start();
    }
}
