package com.guorong.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/")
public class FeignProviderController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("getOrderNo/{orderNo}")
    public String getOrderNo(@PathVariable("orderNo") String orderNo) {
        return String.format("%s-:%s接收到订单号：%s", appName, port, orderNo);
    }
}
