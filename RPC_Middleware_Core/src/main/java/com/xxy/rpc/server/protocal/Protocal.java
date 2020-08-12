package com.xxy.rpc.server.protocal;

import com.xxy.URL;
import com.xxy.entity.Invocation;


public interface Protocal {
    /**
     * 远程调用
     * @param url
     * @param invocation
     */
    Object invokeProtocl(URL url, Invocation invocation);

    /**
     * 服务开启
     * @param url
     */
    void start(URL url);
}
