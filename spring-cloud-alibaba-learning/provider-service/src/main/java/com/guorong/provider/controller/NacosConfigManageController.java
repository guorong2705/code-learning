package com.guorong.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@Slf4j
@RestController
@RequestMapping("/nacos/config/")
public class NacosConfigManageController {

    @Value("${user.name:}")
    private String userName;
    @Value("${user.age:}")
    private Integer userAge;
    @GetMapping("getNacosConfig")
    public String getNacosConfig() {
        return String.format("userName={%s}  userAge={%s}", userName, userAge);
    }

}
