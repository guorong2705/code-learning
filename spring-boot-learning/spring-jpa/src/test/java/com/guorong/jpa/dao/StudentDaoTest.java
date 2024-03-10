package com.guorong.jpa.dao;

import com.guorong.jpa.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StudentDaoTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private StudentDao studentDao;


    @Test
    public void testFindAll() {
        List<Student> studentList = studentDao.findAll();
        log.info(studentList.toString());
    }


    @Test
    public void testSave() {

        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setName("张三-" + i);
            student.setAge(i);
            studentDao.save(student);
        }

    }


    @Test
    public void test() {
        StudentDao bean = context.getBean(StudentDao.class);
        log.info(bean.getClass().getName());
    }


}
