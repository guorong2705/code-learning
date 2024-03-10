package com.guorong.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author guorong
 * @date 2020-05-13
 */
@Setter
@Getter
@ToString
public class User implements Serializable {

    /**
     * 主键
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public User() {}

    public User(Long userId, String username, String nickname, String password, Integer age, LocalDateTime createTime, LocalDateTime updateTime) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.age = age;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
