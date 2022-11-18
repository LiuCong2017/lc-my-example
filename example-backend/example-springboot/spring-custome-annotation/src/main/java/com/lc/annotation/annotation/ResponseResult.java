package com.lc.annotation.annotation;

import java.lang.annotation.*;

/**
 * 响应包装自定义注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
}
