package com.guorong.demo.lifecycle_callback;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifecycleCallbackConfig {

    @Bean
    public InitBean initBean() {
        return new InitBean();
    }
}
