package com.guorong.upload.download.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * - @ColumnWidth(10) // 单元格宽度
 * - @HeadRowHeight(20) // 表头行高
 * - @ContentRowHeight(10) // 内容行高
 * @author guorong
 * @date 2021-05-15
 */
@Data
@HeadRowHeight(30)
@ContentRowHeight(15)
public class Student {

    @ColumnWidth(15)
    @ExcelProperty(value = "学生编号", index = 1)
    private String id;

    @ColumnWidth(15)
    @ExcelProperty(value = "学生姓名", index = 2)
    private String name;

    @ColumnWidth(15)
    @ExcelProperty(value = "学生年龄", index = 3)
    private Integer age;

    public Student() {}

    public Student(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
