package com.xxy.rpc.config;

import com.xxy.URL;
import com.xxy.api.Invoker;
import com.xxy.config.ReferenceConfigBase;
import com.xxy.rpc.RpcBootStrap;
import com.xxy.rpc.registry.RpcRegistryCoreHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XXY
 * @Date: 2020/7/17 21:02
 */
public class ReferenceConfig extends ReferenceConfigBase {
    private RpcBootStrap bootstrap;
    private transient volatile Invoker invoker;
    private transient volatile Object ref;
    private transient volatile boolean initialized;
    private static final RpcRegistryCoreHandler HANDLER = new RpcRegistryCoreHandler();

    public void setBootstrap(RpcBootStrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public synchronized Object get(){
        if(ref == null){
            init();
        }
        return ref;
    }
    public synchronized void init(){
        if(initialized){
            return;
        }

        //拼接 url
        Map<String, String> map = new HashMap<String, String>();


    }

    private Object createProxy(Map<String, String> map){
        //加载注册中心
        checkRegistry();
        //引用服务
        URL registryUrl = getRegistryConfig().toUrl();
        invoker = HANDLER.refer();
        return null;
    }
}
