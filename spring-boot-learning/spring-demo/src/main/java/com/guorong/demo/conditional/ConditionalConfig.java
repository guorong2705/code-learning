package com.guorong.demo.conditional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConditionalConfig {

    @Bean
    @GuoConditionalOnClassName("java.lang.String01")
    public Person person() {
        log.info("person bean created --->>>");
        return new Person("哈哈哈");
    }


    @Data
    @AllArgsConstructor
    public static class Person {
        private String value;
    }
}
