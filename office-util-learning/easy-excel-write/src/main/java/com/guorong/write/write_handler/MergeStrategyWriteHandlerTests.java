package com.guorong.write.write_handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
import com.guorong.write.util.ClassPathUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MergeStrategyWriteHandlerTests {

    private static List<Student> studentData = new ArrayList<>();

    static {
        // 学生数据
        studentData.add(new Student("z01", "aaa", 21));
        studentData.add(new Student("z02", "aaa", 22));
        studentData.add(new Student("z03", "aaa", 23));
    }

    // 测试一次合并效果
    @Test
    void testOnceAbsoluteMergeStrategy() {
        int totalAge = 0;
        for (Student student : studentData) {
            totalAge += student.getAge();
        }
        Student total = new Student();
        total.setId("年龄总计");
        total.setAge(totalAge);
        studentData.add(total);
        int rowIndex = studentData.size();
        // 一次合并
        OnceAbsoluteMergeStrategy mergeStrategy = new OnceAbsoluteMergeStrategy(rowIndex, rowIndex,0,1);
        // 写出
        String fileName = ClassPathUtils.getClassPath()
                .concat("student")
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");
        EasyExcel.write(fileName, Student.class)
                .registerWriteHandler(mergeStrategy)
                .sheet("合并单元格测试")
                .doWrite(studentData);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @HeadFontStyle(fontHeightInPoints = 13) // 表头字体样式
    @ContentFontStyle(fontHeightInPoints = 13) // 数据内容字体样式
    @HeadRowHeight(15) // 表头行高
    @ContentRowHeight(15) // 内容行高
    static class Student {

        @ColumnWidth(15) // 单元格宽度
        @ExcelProperty(value = "学生编号")
        private String id;

        @ColumnWidth(3) // 单元格宽度 字符个数，实际显示会小一点，差值在0.62
        @ExcelProperty(value = "aa")
        private String name;

        @ColumnWidth(15) // 单元格宽度
        @ExcelProperty(value = "学生年龄")
        private Integer age;
    }
}
