package com.littlemonster.chushiwiki.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: white-zhou
 * @date: 2024-04-03 11:23
 * @description: Swagger配置类
 **/
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${spring.application.name:我的应用}")
    private String applicationName;

    @Bean
    public OpenAPI springOpenApi() {
        return new OpenAPI()
                .info(new Info().title(applicationName)
                        .description("初识知识库接口文档")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("小小怪出品"));
    }
}
