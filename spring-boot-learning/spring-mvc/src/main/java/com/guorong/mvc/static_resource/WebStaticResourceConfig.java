package com.guorong.mvc.static_resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web静态资源访问配置
 */
@Configuration
public class WebStaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/my-static/**") // 访问路径:例如：http://127.0.0.1:8089/my-static/love.jpg
                .addResourceLocations("classpath:/static-resources/"); // 静态网页资源位置
    }

}
