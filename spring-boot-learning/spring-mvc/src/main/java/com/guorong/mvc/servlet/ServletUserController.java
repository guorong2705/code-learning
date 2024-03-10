package com.guorong.mvc.servlet;

import com.guorong.mvc.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/servlet/")
public class ServletUserController {

    @GetMapping("getUser")
    public Person getUser() {
        log.info("{} --->>> getUser()", getClass().getName());
        return new Person("张三",12);
    }

    @PostMapping("addUser")
    public String addUser(@RequestBody Person person) {
        log.info("{} --->>> addUser()", getClass().getName());
        return "success";
    }
}
