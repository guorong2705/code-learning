package com.guorong.mvc.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务
 */
@Slf4j
@Component
public class AsyncTaskService {

    /**
     * 异步执行的方法没有返回值
     */
    @Async(SpringAsyncConfig.THREAD_POOL_NAME)
    public void notCallValue() {
        log.info("{} 方法执行开始 --->>>", getClass().getSimpleName());
        try {
            TimeUnit.SECONDS.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} 方法执行结束 --->>>", getClass().getSimpleName());
    }

    /**
     * 异步方法执行,有返回值
     */
    @Async(SpringAsyncConfig.THREAD_POOL_NAME)
    public Future<String> callValue() {
        log.info("{} 方法执行开始 --->>>", getClass().getSimpleName());
        try {
            TimeUnit.SECONDS.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} 方法执行结束 --->>>", getClass().getSimpleName());
        return CompletableFuture.completedFuture("success");
    }

}
