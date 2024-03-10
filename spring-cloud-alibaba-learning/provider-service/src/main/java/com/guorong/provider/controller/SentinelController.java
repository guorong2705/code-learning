package com.guorong.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel/")
public class SentinelController {

    @Value("${server.port}")
    private Integer port;

    @SentinelResource(value = "hello", blockHandler = "blockHandler")
    @GetMapping("hello")
    public String hello() {
        return String.format("provider-%s say hello", port);
    }

    public String blockHandler(BlockException ex) {
        return "block handler";
    }


}
