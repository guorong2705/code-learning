package com.guorng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("run Graph Service");
        SpringApplication.run(Application.class, args);
    }
}
