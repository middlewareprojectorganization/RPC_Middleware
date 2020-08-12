package com.xxy.api;

import com.xxy.URL;

/**
 * @Author: XXY
 * @Date: 2020/8/9 21:57
 */
public interface ProxyFactory {
    /**
     * create proxy.
     *
     * @param invoker
     * @return proxy
     */
    <T> T getProxy(Invoker invoker) throws Exception;

    /**
     * 获取invoker
     * @param proxy
     * @param type
     * @param url
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> Invoker getInvoker(T proxy, Class<T> type, URL url) throws Exception;
}
