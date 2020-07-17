package com.xxy.rpc.spring.annotation;

import com.xxy.rpc.spring.registar.RpcComponentRegistrar;
import com.xxy.rpc.spring.registar.RpcConfigRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: XXY
 * @Date: 2020/7/18 0:04
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(RpcConfigRegistrar.class)
public @interface EnableConfig {

}
