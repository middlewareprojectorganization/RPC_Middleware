package com.xxy.rpc.config.annotation;

import java.lang.annotation.*;

/**
 * @Author: XXY
 * @Date: 2020/5/31 1:11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface RpcService {
}
