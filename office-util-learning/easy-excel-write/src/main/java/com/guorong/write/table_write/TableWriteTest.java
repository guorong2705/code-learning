package com.guorong.write.table_write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.guorong.write.entity.Car;
import com.guorong.write.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 使用table去写入
 * @author guorong
 * @date 2021-05-16
 */
class TableWriteTest {

    private static List<Student> studentData = new ArrayList<>();
    private static List<Car> carData = new ArrayList<>();

    static {
        // 学生数据
        studentData.add(new Student("01", "周杰伦", 21));
        studentData.add(new Student("02", "王力宏", 22));
        studentData.add(new Student("03", "林俊杰", 23));
        studentData.add(new Student("04", "蔡徐坤", 24));
        studentData.add(new Student("05", "罗志祥", 25));
        studentData.add(new Student("06", "谢冬玲", 26));
        // 汽车数据
        carData.add(new Car("宝马", Double.valueOf(21)));
        carData.add(new Car("奔驰", Double.valueOf(22)));
        carData.add(new Car("迪奥", Double.valueOf(23)));
        carData.add(new Car("神驹", Double.valueOf(24)));
    }


    //===============================================================

    @Test
    void writeTable01() {
        String fileName = this.getClass()
                .getResource("/").getPath()
                .concat("student").concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName).excelType(ExcelTypeEnum.XLSX).build();
            // 创建sheet
            WriteSheet writeSheet = EasyExcel.writerSheet("数据").build();
            // 创建table
            WriteTable studentTable = EasyExcel.writerTable(0).head(Student.class).build();
            // relativeHeadRowIndex(10) 相对于工作表的现有内容写头。 索引从零开始。(相对于上一张table内容的空格多少行)
            WriteTable carTable = EasyExcel.writerTable(1).head(Car.class).relativeHeadRowIndex(3).build();
            // 写出数据
            excelWriter.write(studentData, writeSheet, studentTable);
            excelWriter.write(carData, writeSheet, carTable);
        } finally {
            if (Objects.nonNull(excelWriter)) {
                excelWriter.finish();
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @HeadRowHeight(20) // 表头高度
    @ContentRowHeight(12) // 内容高度
    static class Car {

        @ColumnWidth(15) // 设置单元格宽度
        @ExcelProperty(value = "品牌")
        private String brand;

        @ColumnWidth(15)
        @ExcelProperty(value = "价格")
        private Double price;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @HeadRowHeight(30) // 表头行高
    @ContentRowHeight(15) // 内容行高
    static class Student {

        @ColumnWidth(15) // 单元格宽度
        @ExcelProperty(value = "学生编号")
        private String id;

        @ColumnWidth(15) // 单元格宽度
        @ExcelProperty(value = "学生姓名")
        private String name;

        @ColumnWidth(15) // 单元格宽度
        @ExcelProperty(value = "学生年龄")
        private Integer age;
    }

}
