package com.lc.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/19 14:04]
 */
@Aspect
public class Audience {

    @Pointcut("execution(* com.lc.aop.service.Performance.perform(..))")
    public void perform(){}

    @Before("perform()")
    public void takeSeats(){
        System.out.println("执行前置方法");
    }

    @After("perform()")
    public void finish(){
        System.out.println("执行后置方法");
    }

    @AfterReturning("perform()")
    public void applause(){
        System.out.println("执行成功后的返回方法");
    }

    @AfterThrowing("perform()")
    public void demandRefund(){
        System.out.println("执行出错后的方法");
    }

    @Around("perform()")
    public void watchPerform(ProceedingJoinPoint joinPoint){
        try {
            System.out.println("这是环绕方法1");
            joinPoint.proceed();
            System.out.println("这是环绕方法2");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("环绕方法执行完毕");
        }
    }

}
