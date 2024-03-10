package com.guorong.mvc.async;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Future;

@Slf4j
@SpringBootTest
public class AsyncTaskServiceTest {

    @Autowired
    private AsyncTaskService asyncTaskService;

    // 测试异步存在返回值
    @SneakyThrows
    @Test
    public void testCallValue() {
        Future<String> future = asyncTaskService.callValue();
        String value = future.get();
        log.info("异步任务执行返回值 --->>> {}", value);
    }

    // 异步不存在返回值
    @SneakyThrows
    @Test
    public void testNotCallValue() {
        asyncTaskService.notCallValue();
        log.info("异步任务执行返回值 --->>>");
    }

}
