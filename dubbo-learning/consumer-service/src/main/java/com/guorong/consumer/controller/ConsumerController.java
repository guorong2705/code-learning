package com.guorong.consumer.controller;

import com.guorong.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;


    @GetMapping("sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return consumerService.sayHello(name);
    }
}
