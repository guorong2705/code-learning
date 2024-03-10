package com.guorong.consumer.controller;

import com.guorong.provider.dto.UserDto;
import com.guorong.provider.service.DubboUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dubbo/")
public class DubboController {

    @DubboReference(version = DubboUserService.VERSION_1_0_0)
    private DubboUserService dubboUserService;


    @GetMapping("getAllUser")
    public List<UserDto> getAllUser() {
        return dubboUserService.getAllUser();
    }
}
