package com.guorong.consumer.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.UUID;

/**
 * 自定义 feign 请求拦截器添加一些逻辑
 */
public class CustomRequestInterceptor implements RequestInterceptor {

    private static final String TOKEN_KEY = "token";

    @Override
    public void apply(RequestTemplate template) {
        template.header(TOKEN_KEY, UUID.randomUUID().toString());
    }
}
