package com.guorong.swagger.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/**
 * swagger基础配置
 */
@EnableSwagger2
public abstract class BaseSwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Access-Token";

    @Bean
    public Docket createRestApi() {
        SwaggerProperties swaggerProperties = swaggerProperties();
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.getEnable())
                .apiInfo(apiInfo(swaggerProperties))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }

    public ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getApplicationName() + " api document")
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }

    private List<ApiKey> securitySchemes() {
        ApiKey apiKey = new ApiKey(AUTHORIZATION_HEADER, "token", "header");
        return Arrays.asList(apiKey);
    }

    private List<SecurityContext> securityContexts() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex("^(?!auth).*$"))
                .forPaths(PathSelectors.any())
                .build();
        return Arrays.asList(securityContext);
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopeList = {authorizationScope};
        return Arrays.asList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopeList));
    }

    public abstract SwaggerProperties swaggerProperties();
}
