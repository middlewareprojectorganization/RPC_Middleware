package com.xxy.rpc.spring.annotation;

import com.xxy.rpc.spring.registar.RpcComponentRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: XXY
 * @Date: 2020/7/18 0:14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RpcComponentRegistrar.class)
public @interface EnableComponentScan {
    //包扫描路径
    String[] basePackages() default {};

    //类路径
    Class<?>[] basePackageClasses() default {};
}
