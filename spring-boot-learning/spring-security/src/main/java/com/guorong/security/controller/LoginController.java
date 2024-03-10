package com.guorong.security.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guorong
 * @date 2020-11-07
 */
@RestController
public class LoginController {


    @PostMapping("/login-success")
    public String loginSuccess() {
        return "登录成功...";
    }


    @PostMapping("/login-fail")
    public String loginFail() {
        return "登录失败...";
    }




}
