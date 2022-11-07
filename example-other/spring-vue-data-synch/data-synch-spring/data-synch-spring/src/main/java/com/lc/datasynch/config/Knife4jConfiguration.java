package com.lc.datasynch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @version : [v1.0]
 * @description : []
 * 访问地址：http://localhost:xxxx/csm/doc.html
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    private static final String SPLITOR = ";";

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //描述字段支持Markdown语法
                        .description("# misas RESTful APIs")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("MISAS资产管理系统")
                .select()
                //这里指定Controller扫描包路径
                .apis(basePackage("com.misas.controller"+ SPLITOR +"com.misas.common.controller"+ SPLITOR + "com.misas.datav.controller"+ SPLITOR))
//                .apis(RequestHandlerSelectors.basePackage("com.misas.controller"))
//                .apis(RequestHandlerSelectors.basePackage("com.misas.common.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    /**
     * @description 重写basePackage方法，使能够实现多包访问
     * @param basePackage 所有包路径
     * @return Predicate<RequestHandler>
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).map(handlerPackage(basePackage)::apply).orElse(true);
    }

    /**
     * @description 重写basePackage方法，使能够实现多包访问
     * @param basePackage 所有包路径
     * @return Function<Class<?>, Boolean>
     */
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SPLITOR)) {
                assert input != null;
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * @author luoyu
     * @description 重写basePackage方法，使能够实现多包访问
     * @param input
     * @return Optional<? extends Class<?>>
     */
    private static Optional<Class<?>> declaringClass(RequestHandler input) {
        return Optional.ofNullable(input.declaringClass());
    }

}
