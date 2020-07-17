package com.xxy.rpc.spring.listener;

import com.xxy.rpc.RpcBootStrap;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

/**
 * @Author: XXY
 * @Date: 2020/7/18 0:44
 */
public class BootstrapApplicationListener  implements ApplicationListener, ApplicationContextAware,  Ordered {
    private ApplicationContext applicationContext;
    private final RpcBootStrap rpcBootStrap;
    public BootstrapApplicationListener(){
        this.rpcBootStrap = RpcBootStrap.getInstance();
    }
    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            onContextRefreshedEvent((ContextRefreshedEvent) event);
        } else if (event instanceof ContextClosedEvent) {
            onContextClosedEvent((ContextClosedEvent) event);
        }
    }
    private void onContextRefreshedEvent(ContextRefreshedEvent event) {
        rpcBootStrap.start();
    }

    private void onContextClosedEvent(ContextClosedEvent event) {
        rpcBootStrap.stop();
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
