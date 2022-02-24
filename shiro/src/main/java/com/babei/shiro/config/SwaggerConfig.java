package com.babei.shiro.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 *@Author ba'bei
 *@Date 2021/10/14
 **/
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("4.0版本")
                .select()
                /*
                RequestHandlerSelectors，配置要扫描接口的方式
                 basePackage 扫描该路径下的所有包
                 any() 扫描全部
                 none() 都不扫描
                 withClassAnnotation 扫描类上的注解  参数是一个注解的反射对象
                 withClassAnnotation 扫描方法上的注解*/
                .apis(RequestHandlerSelectors.basePackage("com.babei.product.controller")).paths(PathSelectors.any())
                //.paths() 过滤什么路径
                .build();
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Product接口API")
                .description("Product接口API描述")
                .version("4.0")
                .build();
    }
}
