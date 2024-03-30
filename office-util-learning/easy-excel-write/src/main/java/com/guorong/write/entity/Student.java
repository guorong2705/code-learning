package com.guorong.write.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * - @ColumnWidth(10) // 单元格宽度
 * - @HeadRowHeight(20) // 表头行高
 * - @ContentRowHeight(10) // 内容行高
 * @author guorong
 * @date 2021-05-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@HeadRowHeight(30)
@ContentRowHeight(15)
public class Student {

    @ColumnWidth(15)
    @ExcelProperty(value = "学生编号")
    private String id;

    @ColumnWidth(15)
    @ExcelProperty(value = "学生姓名")
    private String name;

    @ColumnWidth(15)
    @ExcelProperty(value = "学生年龄")
    private Integer age;

}
