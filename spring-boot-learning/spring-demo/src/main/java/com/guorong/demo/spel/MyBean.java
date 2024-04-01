package com.guorong.demo.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author guorong
 * @Description
 **/
@Component
public class MyBean {
    @Value("#{testBean.content}")
    private String content;

    @PostConstruct
    public void init() {
        System.out.println("--->>>");
    }
}
