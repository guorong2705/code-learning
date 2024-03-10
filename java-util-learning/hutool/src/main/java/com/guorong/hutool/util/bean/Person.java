package com.guorong.hutool.util.bean;

import lombok.Data;

/**
 * @author guorong
 * @date 2021-05-24
 */
@Data
class Person {
    /**
     * 编号
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 车
     */
    private Car car;

    public Person() {}

    public Person(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
