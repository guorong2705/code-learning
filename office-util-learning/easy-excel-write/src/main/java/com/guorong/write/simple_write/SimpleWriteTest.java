package com.guorong.write.simple_write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.guorong.write.util.ClassPathUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 写操作测试
 */
class SimpleWriteTest {

    private static List<Student> studentData = new ArrayList<>();

    private static List<Car> carData = new ArrayList<>();

    static {
        // 学生数据
        studentData.add(new Student("01", "周杰伦", 21));
        studentData.add(new Student("02", "王力宏", 22));
        studentData.add(new Student("03", "林俊杰", 25));
        // 汽车数据
        carData.add(new Car("宝马", Double.valueOf(21)));
        carData.add(new Car("奔驰", Double.valueOf(22)));
        carData.add(new Car("迪奥", Double.valueOf(23)));
        carData.add(new Car("神驹", Double.valueOf(24)));
    }


    //===============================================================

    /**
     * 写出全部字段(一个sheet)
     */
    @Test
    public void writeAllField01() {
        String fileName = ClassPathUtils.getClassPath()
                .concat("student")
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");

        // 写出数据到excel
        EasyExcel.write(fileName)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("学生数据")
                .head(Student.class)
                .doWrite(studentData);
    }


    /**
     * 写出全部字段(一个sheet)
     */
    @Test
    void writeAllField02() {
        String fileName = ClassPathUtils.getClassPath()
                .concat("student")
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel
                    .write(fileName)
                    .excelType(ExcelTypeEnum.XLSX)
                    .build();

            // WriteSheet
            WriteSheet writeSheet = EasyExcel
                    .writerSheet("学生数据")
                    .head(Student.class)
                    .build();

            // 写出数据
            excelWriter.write(studentData, writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (Objects.nonNull(excelWriter)) {
                excelWriter.finish();
            }
        }
    }


    /**
     * 指定排除的字段
     */
    @Test
    void excludeFieldWrite() {
        String fileName = ClassPathUtils.getClassPath()
                .concat("excludeField")
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");

        // 排除的字段名
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("age");

        // 写出文件，这里会自动关闭流
        EasyExcel
                .write(fileName)
                .excelType(ExcelTypeEnum.XLSX)
                .excludeColumnFiledNames(excludeColumnFiledNames)
                .sheet("学生数据")
                .head(Student.class)
                .doWrite(studentData);
    }





    /**
     * 生成部分标题的列表
     * 根据传入的参数指定包含的字段
     */
    @Test
    void includeFieldWrite() {
        // 文件名称
        String fileName = ClassPathUtils.getClassPath()
                .concat("includeField")
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");

        // 包含的字段
        List<String> includeColumnFiledNames = new ArrayList<>();
        includeColumnFiledNames.add("id");
        includeColumnFiledNames.add("age");

        // 写出文件，这里会自动关闭流
        EasyExcel
                .write(fileName, Student.class)
                .excelType(ExcelTypeEnum.XLSX)
                .includeColumnFiledNames(includeColumnFiledNames)
                .sheet("学生数据")
                .doWrite(studentData);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @HeadRowHeight(20) // 表头高度
    @ContentRowHeight(12) // 内容高度
    public static class Car {

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
    public static class Student {

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
