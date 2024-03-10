package com.guorong.swagger.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties
                .builder()
                .enable(true)
                .applicationName("09-swagger")
                .version("V1.2.2")
                .description("项目表述")
                .build();
    }
}
