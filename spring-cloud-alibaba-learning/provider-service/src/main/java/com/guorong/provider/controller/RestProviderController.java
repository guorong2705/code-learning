package com.guorong.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/rest/")
public class RestProviderController {
    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping(value = "hello")
    public String hello(@RequestParam("name") String name) {
        return appName + " : " + port + "说hello";
    }




}
