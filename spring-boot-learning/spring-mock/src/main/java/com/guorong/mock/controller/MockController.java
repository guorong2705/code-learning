package com.guorong.mock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock/")
public class MockController {

    @GetMapping("hello_world")
    public String hellWorld() {
        return "hello world";
    }

}
