package com.guorong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("run Waybill Service");
        SpringApplication.run(Application.class, args);
    }
}
