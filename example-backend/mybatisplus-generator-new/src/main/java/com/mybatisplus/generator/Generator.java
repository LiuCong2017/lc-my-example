package com.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/auth-test?useUnicode=true&useSSL=false&characterEncoding=utf8";
        String username = "root";
        String password = "root";

        FastAutoGenerator.create(url,username,password)
                .globalConfig(builder -> {
                    builder.author("Kavin") //Set Author
                            .enableSwagger() //Open swagger
                            .outputDir("dist"); //Output directory
                })
                .packageConfig(builder -> {
                    builder.parent("com.security.demo") //package name
                            .moduleName("") //module name
                            .pathInfo(Collections.singletonMap(OutputFile.xml,"dist")); //Set mapperXml generate path
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user","user_role","role") //设置需要生成的表名
                            .addTablePrefix(); //设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
