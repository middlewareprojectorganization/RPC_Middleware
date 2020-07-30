package com.xxy.rpc.registry;


import com.xxy.URL;
import com.xxy.api.Invoker;

/**
 * @Author: XXY
 * @Date: 2020/7/27 23:59
 */
public class RpcRegistryCoreHandler {
    public Invoker export(){
        //先注册
        //导出服务
        //启动服务器
        return null;
    }
    public Invoker refer(URL url){
        //先注册
        Registry registry = beginRegistry(url);
        //获取想要引用服务的URL
        URL serviceUrl = fetchReferServiceUrl(registry);
        //获得能引用服务的invoker
        return createInvoker(serviceUrl);
    }

    private Invoker createInvoker(URL serviceUrl) {
        return null;
    }

    private URL fetchReferServiceUrl(Registry registry) {
        return null;
    }

    private Registry beginRegistry(URL url) {
        return null;
    }
}
