package com.xxy.netty;

import com.xxy.URL;

/**
 * @Author: XXY
 * @Date: 2020/7/31 22:18
 */
public abstract class AbstractClient {
    protected final URL url;
    public AbstractClient(URL url){
        this.url = url;
    }
    public abstract void connect() throws InterruptedException;
    public abstract void doOpen();

}
