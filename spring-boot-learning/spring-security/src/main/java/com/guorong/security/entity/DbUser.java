package com.guorong.security.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guorong
 * @date 2020-11-09
 */
@Data
public class DbUser implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 全名
     */
    private String fullName;

    /**
     * 手机号
     */
    private String mobile;

    public DbUser(String username, String password, String fullName, String mobile) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.mobile = mobile;
    }
}
