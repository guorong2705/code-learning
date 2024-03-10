package com.guorong.rocketmq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RocketmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RocketmqApplication.class, args);
    }
}
