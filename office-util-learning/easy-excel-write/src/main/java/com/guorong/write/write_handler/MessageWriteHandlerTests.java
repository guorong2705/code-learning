package com.guorong.write.write_handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
import com.guorong.write.util.ClassPathUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MessageWriteHandlerTests {

    private static List<Student> studentData = new ArrayList<>();

    static {
        // 学生数据
        studentData.add(new Student("01", "周杰伦", 21));
        studentData.add(new Student("02", "王力宏", 22));
        studentData.add(new Student("03", "林俊杰", 23));
    }

    // 测试一次合并效果
    @Test
    void testOnceAbsoluteMergeStrategy() {
        int totalAge = 0;
        for (Student student : studentData) {
            totalAge += student.getAge();
        }
        int rowIndex = studentData.size() + 1;
        // 一次合并
        OnceAbsoluteMergeStrategy mergeStrategy = new OnceAbsoluteMergeStrategy(rowIndex, rowIndex,0,1);
        Student total = new Student();
        total.setId("年龄总计");
        total.setAge(totalAge);
        studentData.add(total);
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
