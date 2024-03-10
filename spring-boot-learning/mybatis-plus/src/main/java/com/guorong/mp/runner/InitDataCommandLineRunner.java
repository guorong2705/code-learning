package com.guorong.mp.runner;

import com.guorong.mp.entity.DynamicTable;
import com.guorong.mp.entity.Student;
import com.guorong.mp.mapper.DynamicTableMapper;
import com.guorong.mp.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 初始化数据
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class InitDataCommandLineRunner implements CommandLineRunner {

    private final StudentMapper studentMapper;

    private final DynamicTableMapper dynamicTableMapper;

    @Value("${spring.profiles.active}")
    private String activeType;

    @Override
    public void run(String... args) throws Exception {
        if (!Objects.equals("h2", activeType)) {
            return;
        }
        log.info("begin init h2 data ----------->>>");
        // 用户数据
        studentMapper.insert(new Student("Jone", 10, "test1@baomidou.com"));
        studentMapper.insert(new Student("Jack", 15, "test2@baomidou.com"));
        studentMapper.insert(new Student("Tom", 16, "test3@baomidou.com"));
        studentMapper.insert(new Student("Sandy", 21, "test4@baomidou.com"));
        studentMapper.insert(new Student("Billie", 24, "test5@baomidou.com"));
        studentMapper.insert(new Student("Sandy", 28, "test6@baomidou.com"));
        // 动态表名数据
        dynamicTableMapper.insert(new DynamicTable(1L, "记录-01"));
        dynamicTableMapper.insert(new DynamicTable(2L, "记录-02"));
        dynamicTableMapper.insert(new DynamicTable(3L, "记录-03"));
        log.info("end init h2 data ----------->>>");
    }
}
