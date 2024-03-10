package com.guorong.mvc.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Spring异步配置
 */
@EnableAsync // 启用异步支持
@Configuration
public class SpringAsyncConfig {
    // 核心线程数
    private static final int CORE_POOL_SIZE = 8;
    // 最大线程数
    private static final int MAX_POOL_SIZE = 20;

    public static final String THREAD_POOL_NAME = "myThreadPool";

    @Bean(THREAD_POOL_NAME)
    public Executor threadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        return executor;
    }
}
