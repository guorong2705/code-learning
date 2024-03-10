package com.guorong.sentinel.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class OrderServiceSentinelTest {

    @Autowired
    private OrderService orderService;

    @SneakyThrows
    @Test
    public void testLimit() {
        while (true) {
          orderService.listOrderNo();
          TimeUnit.MILLISECONDS.sleep(300);
        }
    }

}
