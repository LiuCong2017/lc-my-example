package com.lc.aop.config;

import com.lc.aop.aspect.Audience;
import com.lc.aop.service.impl.Drama;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/19 14:09]
 */
@Configuration
@EnableAspectJAutoProxy //enable auto proxy(jdk/cglib)
@ComponentScan
public class DramaConfig {

    /**
     * Declare a bean which is using @Aspect annotation
     */
    @Bean
    public Audience audience(){
        return new Audience();
    }

}
