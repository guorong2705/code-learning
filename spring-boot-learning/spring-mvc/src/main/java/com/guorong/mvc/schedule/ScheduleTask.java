package com.guorong.mvc.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 */
@Slf4j
@Component
public class ScheduleTask {
    @Scheduled(cron = "*/30 * * * * *")
    public void doSomething() {
        log.info("ScheduleTask 执行了方法 -- doSomething()");
    }

}
