package com.guorong.mp.other;

import com.guorong.mp.entity.Student;
import com.guorong.mp.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 自动填充之测试
 */
@Slf4j
@SpringBootTest
public class AutoFillFieldValueTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testFillInsert() {
        // 新增记录
        Student student = new Student("刘亦菲", 30, "LYF@gmail.com");
        int insertCount = studentMapper.insert(student);
        Assertions.assertTrue(insertCount == 1);
        // 查新打印记录
        student = studentMapper.selectById(student.getId());
        Assertions.assertNotNull(student.getCreateTime());
        log.info("student --->>> {}", student);
    }

    @Test
    public void testFillUpdate() {
        // 新增记录
        Student student = new Student("刘亦菲", 30, "LYF@gmail.com");
        int insertCount = studentMapper.insert(student);
        Assertions.assertTrue(insertCount == 1);
        // 判断是否插入更新日期
        student = studentMapper.selectById(student.getId());
        Assertions.assertNull(student.getUpdateTime());
        // 更新
        int age = 10000;
        student.setAge(age);
        int updateCount = studentMapper.updateById(student);
        Assertions.assertTrue(updateCount == 1);
        // 查询更新值
        student = studentMapper.selectById(student.getId());
        Assertions.assertNotNull(student.getUpdateTime());
        log.info("student --->>> {}", student);
    }
}
