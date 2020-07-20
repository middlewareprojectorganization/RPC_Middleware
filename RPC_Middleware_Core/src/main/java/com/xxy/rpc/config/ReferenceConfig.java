package com.xxy.rpc.config;

import com.xxy.api.Invoker;
import com.xxy.config.AbstractConfig;
import com.xxy.rpc.RpcBootStrap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XXY
 * @Date: 2020/7/17 21:02
 */
public class ReferenceConfig extends AbstractConfig {
    private RpcBootStrap bootstrap;
    private transient volatile Invoker invoker;
    private transient volatile Object ref;
    private transient volatile boolean initialized;

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
}
