package com.wanyu.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/*
 * @Description Please describe the role of this class.
 * @Author wan
 * @Date 2019/12/9 16:35
 * @Version 1.0
 */
public class MysqlGenerator {


    private static final String projectPath = System.getProperty("user.dir");

    private static final String parent = "com.wanyu.generator";

    private static final String freemarkerTemplatePath = "/templates/mapper.xml.ftl";

   // private static final String velocityTemplatePath = "/templates/mapper.xml.vm";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig();
        // 数据库信息设置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 包信息设置
        PackageConfig pageConfig = getPackageConfig();
        // 自定义配置
        InjectionConfig injectionConfig = getInjectionConfig();
        // 配置模板
        TemplateConfig templateConfig = getTemplateConfig();
        // 策略配置
        StrategyConfig strategy = getStrategyConfig();

        mpg.setGlobalConfig(globalConfig);
        mpg.setDataSource(dataSourceConfig);
        mpg.setPackageInfo(pageConfig);
        mpg.setCfg(injectionConfig);
        mpg.setTemplate(templateConfig);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    private static GlobalConfig getGlobalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();
        // 设置生成文件的输出目录
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        // 是否覆盖已有文件 默认false
        globalConfig.setFileOverride(false);
        // 是否打开输出目录 默认true
        globalConfig.setOpen(false);
        // 是否在xml中添加二级缓存配置 默认false
        globalConfig.setEnableCache(false);
        // 开发人员
        globalConfig.setAuthor("wan");
        // 开启Kotlin 默认false
        globalConfig.setKotlin(false);
        // 开启Swagger2模式
        globalConfig.setSwagger2(false);
        // 开启 ActiveRecord模式
        globalConfig.setActiveRecord(false);
        // 设置实体名方式
        globalConfig.setEntityName("%sEntity");
        // mapper 接口命名方式
        globalConfig.setMapperName("%sMapper");
        // xml文件命名方式
        globalConfig.setXmlName("%sMapper");
        // service 命名方式
        globalConfig.setServiceName("I%sService");
        // service impl 命名方式
        globalConfig.setServiceImplName("%sServiceImpl");
        // controller命名方式
        globalConfig.setControllerName("%sController");
        // 指定生成的主键的ID类型  建议使用雪花算法，审查各行分布式全局唯一
        globalConfig.setIdType(IdType.ASSIGN_ID);
        return globalConfig;
    }

    private static DataSourceConfig getDataSourceConfig(){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 设置连接url
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybatis-plus-demo?" +
                "useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");

        // 设置驱动名称  这里和pom文件里面引入的mysql-connector-java版本有关系
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        // 设置用户名
        dataSourceConfig.setUsername("root");
        // 设置密码
        dataSourceConfig.setPassword("myroot");
        return dataSourceConfig;
    }

    private static PackageConfig getPackageConfig(){
        PackageConfig packageConfig = new PackageConfig();
        //父包名，如果为空，下面子包名必须写全部，否则就只需要写子包名
        packageConfig.setParent(parent);
        // 设置父包模块名 默认为空，
        packageConfig.setModuleName(null);
        // 设置实体包,controller,service,service.impl,mapper的名字 这些都有默认值，就不在这全部演示了
        return packageConfig;
    }

    private static InjectionConfig getInjectionConfig(){
        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(freemarkerTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        return injectionConfig;
    }

    private static TemplateConfig getTemplateConfig(){

        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        return templateConfig;
    }

    private static StrategyConfig getStrategyConfig(){
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 数据库表映射实体的命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 表字段映射到实体的命名策略，未指定按照naming执行
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 表前缀
        strategyConfig.setTablePrefix("t_");
        // 字段前缀
        //strategyConfig.setFieldPrefix("");
        // 需要排除的表名 自3.3.0起，不在支持正则表达式,请使用notLikeTable
        //strategyConfig.setExclude("t_keywords");
        // 是否为构建者模型,默认false
        strategyConfig.setEntityBuilderModel(true);
        // 是否为lombok模型 默认false
        strategyConfig.setEntityLombokModel(true);
        // 是否生成@ResrController控制器
        strategyConfig.setRestControllerStyle(true);
        // 驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);

        return strategyConfig;
    }
}
