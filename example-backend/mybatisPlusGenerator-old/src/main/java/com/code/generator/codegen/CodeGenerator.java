package com.code.generator.codegen;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

    // 数据库连接配置
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/tb?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String JDBC_USER_NAME = "root";
    private static final String JDBC_PASSOWRD = "root";

    // 包名和模块名
    private static final String PACKAGE_NAME = "com.tb";
    private static final String MODULE_NAME = "";

    // 表名，多个表使用英文逗号分割
    private static final String TBL_NAMES = "form_entity,component_entity,column_entity";

    // 表名的前缀，从表生成代码时会去掉前缀
    private static final String TABLE_PREFIX = "";

    // 生成代码入口main方法
    public static void main(String[] args) {
        // 0.代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1.全局配置
        GlobalConfig gc = getGlobalConfig();
        mpg.setGlobalConfig(gc);

        // 2.数据源配置
        DataSourceConfig dsc = getDataSourceConfig();
        mpg.setDataSource(dsc);

        // 3.包配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);

        // 4.自定义配置
        InjectionConfig cfg = getInjectionConfig();
        mpg.setCfg(cfg);

        // 5.模板配置
        TemplateConfig templateConfig = getTemplateConfig();
        mpg.setTemplate(templateConfig);
        // 使用Freemarker模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 6.策略配置
        StrategyConfig strategy = getStrategyConfig();
        mpg.setStrategy(strategy);

        // 7.开始生成代码
        mpg.execute();
    }

    /** 1.全局配置 */
    private static GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("com.tb");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("lc");
        gc.setOpen(false);
        // 自定义生成的ServiceName，去掉默认的ServiceName前面的I
        gc.setServiceName("%s" + ConstVal.SERVICE);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        return gc;
    }

    /** 2.数据源配置 */
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(JDBC_DRIVER);
        dsc.setUrl(JDBC_URL);
        // dsc.setSchemaName("public");
        dsc.setUsername(JDBC_USER_NAME);
        dsc.setPassword(JDBC_PASSOWRD);
        return dsc;
    }

    /** 3.包配置 */
    private static PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        // 生成PACKAGE_NAME.MODULE_NAME的包路径
        pc.setParent(PACKAGE_NAME);
        pc.setModuleName(MODULE_NAME);
        return pc;
    }

    /** 4.自定义配置 */
    private static InjectionConfig getInjectionConfig() {

        // 这里模板引擎使用的是freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        String projectPath = System.getProperty("com.tb");
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /** 5.模板配置 */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        return templateConfig;
    }


    /** 6.策略配置 */
    private static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        // 下划线转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TBL_NAMES.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(TABLE_PREFIX);
        return strategy;
    }

}