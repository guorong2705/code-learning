package com.guorong.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.guorong.mp.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Slf4j
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;


    @Test
    public void testList() {
        List<Student> studentList = studentService.list();
        Assertions.assertFalse(studentList.isEmpty());
        studentList.forEach(student -> log.info("student --->>> {}", student));
    }


    @Test
    public void testBatchSave() {
        List<Student> studentList = Arrays.asList(
                new Student("Jone", 10, "test1@baomidou.com"),
                new Student("Jack", 15, "test2@baomidou.com"),
                new Student("Tom", 16, "test3@baomidou.com")
        );
        // 批量保存id会回填到插入实体
        studentService.saveBatch(studentList);
        studentList.forEach(student -> Assertions.assertNotNull(student.getId()));
    }

    @Test
    public void test02() {
        LambdaQueryWrapper<Student> queryWrapper = Wrappers.<Student>lambdaQuery()
                .select(Student::getId);
        List<Long> studentIdList = studentService
                .listObjs(queryWrapper, id -> Long.valueOf(String.valueOf(id)));
        Assertions.assertTrue(studentIdList.size() > 0);
        studentIdList.forEach(System.out::println);
    }


}
