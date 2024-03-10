package com.guorong.swagger.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author guorong
 * @date 2021-05-07
 */
@Data
public class Student {

    @ApiModelProperty(value = "姓名", dataType = "String", example = "张三")
    private String name;

    @ApiModelProperty(value = "年龄", dataType = "Integer", example = "28")
    private Integer age;

    public Student() {}

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
