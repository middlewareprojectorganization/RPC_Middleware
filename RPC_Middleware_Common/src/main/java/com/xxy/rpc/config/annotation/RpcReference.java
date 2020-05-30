package com.xxy.rpc.config.annotation;

import java.lang.annotation.*;

/**
 * @Author: XXY
 * @Date: 2020/5/31 1:10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface RpcReference {
}
