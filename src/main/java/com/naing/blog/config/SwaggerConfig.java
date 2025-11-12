package com.naing.blog.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    public OpenAPI swaggerConfig() {
        return new OpenAPI().info(new Info()
                .title("Blog Documentation")
                .version("1.0")
                .description("Documentation for My Spring Boot API"));
    }
}
