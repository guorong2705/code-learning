package com.guorong.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.guorong.write.entity.Car;
import com.guorong.write.entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 重复多次写入
 * @author guorong
 * @date 2021-05-16
 */
public class RepeatedWriteTest {

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


    /**
     * 重复多次写入(写到同一个sheet)
     */
    @Test
    public void repeatedWrite01() {
        String fileName = this.getClass()
                .getResource("/").getPath()
                .concat("student").concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName).excelType(ExcelTypeEnum.XLSX).build();

            // 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("学生数据").head(Student.class).build();

            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < studentData.size(); i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<Student> data = new ArrayList<>();
                data.add(studentData.get(i));
                excelWriter.write(data, writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (Objects.nonNull(excelWriter)) {
                excelWriter.finish();
            }
        }
    }


    /**
     * 重复多次写入(写到不同的sheet, 同一个对象)
     */
    @Test
    public void repeatedWrite02() {
        String fileName = this.getClass()
                .getResource("/").getPath()
                .concat("student").concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName).excelType(ExcelTypeEnum.XLSX).build();
            // 实际使用时根据数据库分页的总的页数来。这里最终会写到size个sheet里面
            for (int i = 0; i < studentData.size(); i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "学生数据" + i).head(Student.class).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<Student> data = new ArrayList<>();
                data.add(studentData.get(i));
                // 写出数据到sheet
                excelWriter.write(data, writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (Objects.nonNull(excelWriter)) {
                excelWriter.finish();
            }
        }
    }


    /**
     * 重复多次写入(写到不同的sheet 不同的对象)
     */
    @Test
    public void repeatedWrite03() {
        String fileName = this.getClass()
                .getResource("/").getPath()
                .concat("student").concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName).excelType(ExcelTypeEnum.XLSX).build();
            // 创建sheet, 不同的sheet使用不同的模型
            WriteSheet studentWriteSheet = EasyExcel.writerSheet(0, "学生数据").head(Student.class).build();
            WriteSheet carWriteSheet = EasyExcel.writerSheet(1, "汽车数据").head(Car.class).build();
            // 写出数据
            excelWriter.write(studentData, studentWriteSheet);
            excelWriter.write(carData, carWriteSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (Objects.nonNull(excelWriter)) {
                excelWriter.finish();
            }
        }


    }



}
