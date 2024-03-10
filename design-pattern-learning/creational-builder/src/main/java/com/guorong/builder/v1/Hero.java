package com.guorong.builder.v1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 英雄
 */
@Getter
@Setter
@ToString
class Hero {
    // 职业
    private final String profession;
    // 姓名
    private final String name;
    // 头发类型
    private  String hairType;
    // 头发颜色
    private  String hairColor;
    // 盔甲
    private String armor;
    // 武器
    private String weapon;
    public Hero(String profession, String name) {
        this.profession = profession;
        this.name = name;
    }
}
