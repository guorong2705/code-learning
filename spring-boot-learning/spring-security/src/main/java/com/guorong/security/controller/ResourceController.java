package com.guorong.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author guorong
 * @date 2020-11-07
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {



    @GetMapping("/user/r1")
    public String userR1() {
        return getUsername() + " : user-资源r1";
    }

    @GetMapping("/user/r2")
    public String userR2() {
        return getUsername() + " : user-资源r2";
    }


    @GetMapping("/vip/r1")
    public String vipR1() {
        return getUsername() + " : vip-资源r1";
    }

    @GetMapping("/vip/r2")
    public String vipR2() {
        return getUsername() + " : vip-资源r2";
    }


    /**
     * 获取当前登录的用户名
     * @return
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            // 未认证
            return null;
        }
        // 获取主体
        Object principal = authentication.getPrincipal();
        if (Objects.isNull(principal)) {
            // 主体为空，未登录
            return "匿名";
        }
        if (!(principal instanceof UserDetails)) {
            // 返回主体的toString
            return principal.toString();
        }
        // 主体为 UserDetails 返回登录的用户名
        UserDetails userDetails = (UserDetails) principal;
        return userDetails.getUsername();
    }


}
