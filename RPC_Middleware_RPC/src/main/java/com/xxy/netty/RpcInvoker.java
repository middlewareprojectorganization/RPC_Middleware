package com.xxy.netty;

import com.xxy.URL;
import com.xxy.api.Invoker;
import com.xxy.result.Result;

/**
 * @Author: XXY
 * @Date: 2020/8/3 22:20
 */
public class RpcInvoker implements Invoker {

    private final URL url;
    public RpcInvoker(URL url){
        this.url = url;
    }

    @Override
    public Result invoke() {
        //通过netty返回结果
        return null;
    }
}
