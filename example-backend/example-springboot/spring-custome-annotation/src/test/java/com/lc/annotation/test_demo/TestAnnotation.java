package com.lc.annotation.test_demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD,})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    String test();
    int number() default 66;
}
