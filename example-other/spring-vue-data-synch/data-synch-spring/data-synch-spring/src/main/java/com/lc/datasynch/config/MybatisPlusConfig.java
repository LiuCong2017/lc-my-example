package com.lc.datasynch.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 */
@Configuration
public class MybatisPlusConfig {

    /** 新版mp
     * 创建mp的拦截器，注册到spring容器中
     * **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        /**
         * 配置分页插件
         */
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        /**
         * 3.4.0以后的mp版本，推荐如下的配置
         * 配置乐观锁插件
         */
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        return interceptor;
    }


}
