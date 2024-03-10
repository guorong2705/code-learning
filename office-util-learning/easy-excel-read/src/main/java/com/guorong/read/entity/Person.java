package com.guorong.read.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guorong
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    /**
     * 强制读取第1个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
    @ExcelProperty(index = 0)
    private String id;

    @ExcelProperty(index = 1)
    private String name;

    /**
     * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
     */
    @ExcelProperty(index = 2)
    private Double score;

    @ExcelProperty(index = 3)
    private Integer grade;

    @ExcelProperty(index = 4)
    private Integer age;



}
