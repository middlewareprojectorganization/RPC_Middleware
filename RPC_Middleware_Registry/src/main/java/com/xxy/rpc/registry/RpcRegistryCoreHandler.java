package com.xxy.rpc.registry;


import com.xxy.URL;
import com.xxy.api.Invoker;
import com.xxy.netty.RpcInvoker;
import com.xxy.rpc.RegistryCenterService;
import com.xxy.rpc.RegistryCenterServiceImpl;

/**
 * @Author: XXY
 * @Date: 2020/7/27 23:59
 */
public class RpcRegistryCoreHandler {
    private final RegistryCenterService registryCenterService;
    public RpcRegistryCoreHandler(){
        registryCenterService = new RegistryCenterServiceImpl();
    }
    public Invoker export(){
        //先注册
        //导出服务
        //启动服务器
        return null;
    }
    public Invoker refer(URL url){
        //先注册
         beginRegistry(url);
        //获取想要引用服务的URL
        URL serviceUrl = registryCenterService.getReferServiceInfo(url.getHost(), url.getPort(), url.getParam().get("className").toString());
        //获得能引用服务的invoker
        return createInvoker(serviceUrl);
    }

    private Invoker createInvoker(URL serviceUrl) {
        return new RpcInvoker(serviceUrl);
    }



    private void beginRegistry(URL url) {
        registryCenterService.registry(url);
    }
}
