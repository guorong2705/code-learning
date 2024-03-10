package com.guorong.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guorong.mp.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@SpringBootTest
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 测试排序
     */
    @Test
    public void testOrder() {
        LambdaQueryWrapper<Student> wrapper = Wrappers.<Student>lambdaQuery()
                .orderByDesc(Student::getName);
        List<Student> studentList = studentMapper.selectList(wrapper);
        studentList.forEach(student -> log.info("student --->>> {}", student));
    }

    /**
     * 测试分页
     */
    @Test
    public void testSelectPage() {
        Page<Student> page = studentMapper.selectPage(Page.of(1, 2), null);
        List<Student> records = page.getRecords();
        Assertions.assertEquals(page.getSize(), 2);
        log.info("total={}  pages={}  pageNum={}  pageSize={}", page.getTotal(), page.getPages(), page.getCurrent(), page.getSize());
        records.forEach(s -> log.info("student --->>> {}", s));
    }

    /**
     * 自定义分页查询方法
     */
    @Test
    public void testCustomPage() {
        Student student = new Student();
        student.setName("S");
        IPage<Student> pageVo = studentMapper.findLikePageVo(Page.of(1, 2), student);
        List<Student> records = pageVo.getRecords();
        log.info("current={}  pageSize={}  pages={}  total={}", pageVo.getCurrent(), pageVo.getSize(), pageVo.getPages(), pageVo.getTotal());
        records.forEach(s -> log.info("student --->>> {}", s));
    }

    /**
     * 查询列表
     */
    @Test
    public void testSelectList() {
        List<Student> studentList = studentMapper.selectList(null);
        Assertions.assertFalse(studentList.isEmpty());
        studentList.forEach(student -> log.info("student --->>> {}", student));
    }

    /**
     * 测试查询包装器
     */
    @Test
    public void testSelectListWrapper() {
        LambdaQueryWrapper<Student> queryWrapper = Wrappers.<Student>lambdaQuery()
                .ge(Student::getId, 3);
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        Assertions.assertTrue(!studentList.isEmpty());
        studentList.forEach(student -> log.info("student --->>> {}", student));
    }

    @Test
    public void testInsert() {
        Student student = new Student();
        student.setName("张三");
        student.setEmail("zs@foxmail.com");
        student.setAge(27);
        int insert = studentMapper.insert(student);
        Assertions.assertEquals(1, insert);
        log.info("student --->>> {}", student);
    }

    @Test
    public void testSelectOne() {
        Student student = new Student();
        student.setName("Jone");
        LambdaQueryWrapper<Student> queryWrapper = Wrappers.lambdaQuery(student);
        Student selectOneStudent = studentMapper.selectOne(queryWrapper);
        Assertions.assertEquals(student.getName(), selectOneStudent.getName());
        log.info("student --->>> {}", selectOneStudent);
    }

    @Test
    public void testSelectCount() {
        Long count = studentMapper.selectCount(null);
        Assertions.assertTrue(count > 0);
        log.info("count --->>> {}", count);
    }

    @Test
    public void testDeleteById() {
        int deleteCount = studentMapper.deleteById(1);
        Assertions.assertEquals(1, deleteCount);
        log.info("deleteCount --->>> {}", deleteCount);

        Student student = new Student();
        student.setId(2L);
        student.setName("Billie"); // 这个不起作用
        int count = studentMapper.deleteById(student);
        Assertions.assertEquals(1, count);
        log.info("count --->>> {}", count);
    }

    @Test
    public void testDelete() {
        log.info("删除前记录 ------------------------------->>>");
        List<Student> deleteBeforeList = studentMapper.selectList(null);
        deleteBeforeList.forEach(s -> log.info("student --->>> {}", s));

        // 删除
        LambdaQueryWrapper<Student> queryWrapper = Wrappers.<Student>lambdaQuery()
                .like(Student::getName, "Jack");
        int deleteCount = studentMapper.delete(queryWrapper);
        Assertions.assertTrue(deleteCount > 0);
        log.info("deleteCount --->>> {}", deleteCount);

        log.info("删除后记录 ------------------------------->>>");
        List<Student> studentList = studentMapper.selectList(null);
        studentList.forEach(s -> log.info("student --->>> {}", s));
    }

}
