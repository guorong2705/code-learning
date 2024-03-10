package com.guorong.mp.other;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.guorong.mp.entity.Student;
import com.guorong.mp.mapper.StudentMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 逻辑删除测试
 */
@Slf4j
@SpringBootTest
public class LogicDeleteTest {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ObjectMapper objectMapper;

    // 逻辑上传字段效果
    @Test
    public void testLoginDelete() {
        log.info("全部学生数据---------------->>>");
        studentMapper.selectList(null).forEach(student -> log.info(student.toString()));

        log.info("删除员工Tom --------------->>>");
        LambdaQueryWrapper<Student> deleteQueryWrapper = Wrappers.<Student>lambdaQuery()
                .eq(Student::getName, "Tom");
        studentMapper.delete(deleteQueryWrapper);
        studentMapper.selectList(null).forEach(student -> log.info(student.toString()));
    }

    /**
     * 逻辑删除枚举 JSON 展示
     * - @JsonValue // json序列化显示的值
     *   private final Integer value;
     */
    @SneakyThrows
    @Test
    public void testJsonSerialize() {
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        Student student = studentMapper.selectList(null).get(0);
        System.out.println(objectWriter.writeValueAsString(student));
    }

}
