package com.guorong.mybatis.mapper.master;

import com.guorong.mybatis.entity.master.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@Slf4j
@SpringBootTest
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelectAll() {
        List<User> userList = userMapper.selectAll();
        userList.stream().forEach(user -> log.info(user.toString()));
    }



}
