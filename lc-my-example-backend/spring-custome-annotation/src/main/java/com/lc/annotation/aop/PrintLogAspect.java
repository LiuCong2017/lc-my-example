package com.lc.annotation.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 自定义注解结合AOP切面编程优雅的实现日志打印
 */
@Slf4j
@Component
@Aspect
public class PrintLogAspect {
    @Around(value = "@annotation(com.lc.annotation.annotation.PrintLog)")
    public Object handlerPrintLog(ProceedingJoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        for (Object o:params){
            sb.append(o+"; ");
        }
        log.info("进入《{}》方法，参数为:{}",methodName,sb.toString());

        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("打印日志处理error...",e);
        }
        log.info("{}方法执行结束...",methodName);
        return obj;
    }
}
