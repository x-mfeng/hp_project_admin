package com.lance.hp.hp_study.config;

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
 * @author MaoFengX
 * @version 1.0.0
 * @ClassName Swagger2Config.java
 * @Description Swagger2API文档的配置
 * @createTime 2022/05/19 10:59:00
 */
@Configuration //配置类
@EnableSwagger2 //开启Swagger的自动配置
public class Swagger2Config {
    @Bean //配置docket 以配置Swagger集体参数
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.lance.hp.hp_study.controller"))
                //为有@Api注解的Controller生成API文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("hp_study")
                .description("hp_study")
                .version("1.0")
                .build();
    }
}
