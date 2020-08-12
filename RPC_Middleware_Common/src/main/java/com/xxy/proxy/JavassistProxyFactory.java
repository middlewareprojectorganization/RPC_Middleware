package com.xxy.proxy;

import com.xxy.URL;
import com.xxy.api.Invoker;
import com.xxy.api.ProxyFactory;

import java.lang.reflect.Proxy;


/**
 * @Author: XXY
 * @Date: 2020/8/9 21:59
 */
public class JavassistProxyFactory implements ProxyFactory {
    @Override
    public <T> T getProxy(Invoker invoker) throws Exception {
        return (T)Proxy.newProxyInstance(invoker.getClass().getClassLoader(), invoker.getClass().getInterfaces(), new InvokerInvocationHandler(invoker));
    }



    @Override
    public <T> Invoker getInvoker(T proxy, Class<T> type, URL url) throws Exception {
        return null;
    }
}
