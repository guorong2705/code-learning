package com.guorong.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString(callSuper = true)
@TableName(value = "t_student", autoResultMap = true)
public class Student extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "email")
    private String email;

    // 学生信息
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ClassInfo classInfo;

    public Student() {
    }

    public Student(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
