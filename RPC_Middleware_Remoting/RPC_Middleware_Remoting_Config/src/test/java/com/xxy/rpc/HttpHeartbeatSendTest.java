package com.xxy.rpc;

import com.xxy.rpc.api.tansport.ConfigClient;
import com.xxy.rpc.common.URL;
import com.xxy.rpc.netty4.HttpConfigClient;
import org.junit.Test;

/**
 * @Author: XXY
 * @Date: 2020/6/6 22:39
 */
public class HttpHeartbeatSendTest {

    @Test
    public void testSendHeartBead() throws Exception{
        URL url = new URL("config_center", "127.0.0.1", 8080);
        ConfigClient configClient = new HttpConfigClient(url);
        configClient.getConfig();
    }
}
