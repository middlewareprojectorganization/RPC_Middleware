package com.xxy.rpc;

/**
 * @Author: XXY
 * @Date: 2020/7/17 21:07
 */
public class RpcBootStrap {
    private static RpcBootStrap instance;
    public static synchronized RpcBootStrap getInstance(){
        if(instance == null){
            instance = new RpcBootStrap();
        }
        return instance;
    }

    public void init(){

    }
    public void start(){

    }
}
