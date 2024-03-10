package com.guorong.mp.other;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guorong.mp.entity.Student;
import com.guorong.mp.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * PageHelper 整合 mybatis-plus 测试
 */
@Slf4j
@SpringBootTest
public class PageHelperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testPage() {
        PageHelper.startPage(2, 2);
        List<Student> studentList = studentMapper.selectList(null);
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        log.info("pageNum={}  pageSize={}  pages={} total={} --->>>", pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getPages(), pageInfo.getTotal());
        studentList.forEach(student -> log.info("student --->>> {}", student));
    }
}
