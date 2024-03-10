package com.guorong.mp.other;

import com.guorong.mp.entity.ClassInfo;
import com.guorong.mp.entity.Student;
import com.guorong.mp.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 类型处理器
 */
@Slf4j
@SpringBootTest
public class TypeHandlerTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void test() {
        // 插入数据
        Student student = new Student("张三", 30, "zs@qq.com");
        ClassInfo classInfo = new ClassInfo("G0302", 120, "高三二班");
        student.setClassInfo(classInfo);
        int insertCount = studentMapper.insert(student);
        Assertions.assertEquals(1, insertCount);
        // 获取数据
        Student studentClassInfo = studentMapper.selectById(student.getId());
        Assertions.assertNotNull(studentClassInfo);
        log.info("studentClassInfo --->>> {}", studentClassInfo);
    }

}
