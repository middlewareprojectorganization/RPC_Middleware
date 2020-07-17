package com.xxy.rpc.spring.annotation;

import java.lang.annotation.*;

/**
 * @Author: XXY
 * @Date: 2020/7/18 1:02
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface Service {
}
