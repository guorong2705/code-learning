package com.guorong.mp.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 学生班级信息
 */
@Data
public class ClassInfo implements Serializable {

    public ClassInfo() {}

    public ClassInfo(String classNo, Integer studentCount, String className) {
        this.classNo = classNo;
        this.studentCount = studentCount;
        this.className = className;
    }

    /**
     * 班级编号
     */
    private String classNo;
    /**
     * 学生数量
     */
    private Integer studentCount;
    /**
     * 班级名称
     */
    private String className;
}
