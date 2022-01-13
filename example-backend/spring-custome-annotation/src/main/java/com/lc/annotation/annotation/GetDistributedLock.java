package com.lc.annotation.annotation;

import java.lang.annotation.*;

/**
 * 获取redis分布式锁 注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GetDistributedLock {

    String lockKey(); //分布式锁key
    String lockValue() default "lockValue"; //分布式锁value，默认 localValue
    int expireTime() default 300; //过期时间，默认300秒

}
