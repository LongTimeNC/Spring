package com.samlfatcat.config;


import org.springframework.context.annotation.*;


/**
 * @author zsz
 * @Description
 * @date 2022/6/7
 * ------------@Configuration标志该类是Spring的核心配置类
 *-------------@ComponentScan("com.samlfatcat") 组建扫描！ 相当于<context:component-scan base-package="com.samlfatcat"></context:component-scan>
 *-------------加载外部properties配置文件<context:property-placeholder location="jdbc.properties"></context:property-placeholder>
 *-------------
 */
//标志该类是Spring的核心配置类
@Configuration
//组件扫描
@ComponentScan("com.samlfatcat")
//通过import将数据源配置类加载到核心配置类里面
@Import({DataSourceConfiguration.class})
//AOP配置
@EnableAspectJAutoProxy
public class SpringConfiguration {

}
