package com.xxy.netty;

import com.xxy.URL;

/**
 * @Author: XXY
 * @Date: 2020/7/31 22:18
 */
public abstract class AbstractClient {
    public AbstractClient(URL url){

    }
    public abstract void connect();
    public abstract void doOpen();

}
