package com.guorong.swagger.controller;

import com.guorong.common.ApiResult;
import com.guorong.swagger.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guorong
 * @date 2021-05-07
 */
// @Api(tags = "学生模块")
@RestController
@RequestMapping("/student")
public class StudentController {

    private static List<Student> list = new ArrayList<>();

    static {
        list.add(new Student("张三-01", 21));
        list.add(new Student("张三-02", 22));
        list.add(new Student("张三-03", 23));
        list.add(new Student("张三-04", 24));
        list.add(new Student("张三-05", 25));
    }


    @ApiOperation(value = "添加学生")
    @PostMapping("/addStudent")
    public ApiResult addStudent(@RequestBody Student student) {
        list.add(student);
        return ApiResult.success("添加成功");
    }


    @ApiOperation(value = "获取学生列表", response = ApiResult.class)
    @PostMapping("/listStudent")
    public ApiResult listStudent() {
        return ApiResult.success(list);
    }



}
