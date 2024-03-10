package com.guorong.upload.download.controller;

import com.alibaba.excel.EasyExcel;
import com.guorong.upload.download.entity.Car;
import com.guorong.upload.download.entity.Student;
import com.guorong.upload.download.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guorong
 * @date 2021-05-15
 */
@RestController
@RequestMapping("/excel/download/")
public class ExcelDownloadController {

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


    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * 1. 创建excel对应的实体对象
     * 2. 设置返回的 参数
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        ResponseUtil.setContentType(response, "xlsx");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
        String fileName = URLEncoder.encode("学生数据", "UTF-8").concat(".xlsx");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        // 写入数据到输出流
        EasyExcel.write(response.getOutputStream(), Student.class).sheet("学生数据").doWrite(studentData);
    }



}
