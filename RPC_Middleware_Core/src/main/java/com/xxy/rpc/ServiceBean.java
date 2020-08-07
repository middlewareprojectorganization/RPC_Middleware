package com.xxy.rpc;

import com.xxy.URL;
import com.xxy.rpc.config.ServiceConfig;
import com.xxy.rpc.spring.annotation.Service;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @Author: XXY
 * @Date: 2020/7/18 1:16
 */
public class ServiceBean extends ServiceConfig implements InitializingBean, DisposableBean,
        ApplicationContextAware, BeanNameAware,
        ApplicationEventPublisherAware {

    private final transient Service service;

    private transient ApplicationContext applicationContext;

    private transient String beanName;
    private ApplicationEventPublisher applicationEventPublisher;

    public ServiceBean() {
        super();
        this.service = null;
    }

    public ServiceBean(Service service) {
        this.service = service;
    }
    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }

    @Override
    public URL toUrl() {
        return null;
    }
}
