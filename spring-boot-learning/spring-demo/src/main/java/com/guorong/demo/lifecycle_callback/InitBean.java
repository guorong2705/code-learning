package com.guorong.demo.lifecycle_callback;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public class InitBean {

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct() --->>>");
    }
}
