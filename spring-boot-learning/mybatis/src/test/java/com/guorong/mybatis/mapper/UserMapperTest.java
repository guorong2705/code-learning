package com.guorong.mybatis.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guorong.mybatis.entity.User;
import com.guorong.mybatis.mapper.master.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guorong
 * @date 2020-05-13
 */
@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelectAll() {
        List<User> userList = userMapper.selectAll();
        userList.stream().forEach(user -> log.info(user.toString()));
    }

    @Test
    public void testPageHelper() {
        PageHelper.startPage(1, 2);
        List<User> userList = userMapper.selectAll();
        userList.forEach(user -> log.info(user.toString()));
        PageInfo pageInfo = new PageInfo(userList);
        log.info("page : pageNum={}, pageSize={}, total={}", pageInfo.getPageNum(), pageInfo.getSize(), pageInfo.getTotal());
    }


    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("张三0001");
        user.setPassword("123456");
        userMapper.insertUser(user);
        log.info(user.toString());
    }


    @Test
    public void testInsertBatch() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("张三-01");
        user1.setPassword("123456");
        list.add(user1);
        User user2 = new User();
        user2.setUsername("张三-02");
        user2.setPassword("123456");
        list.add(user2);
        userMapper.insertBatch(list);
        list.stream().forEach(user -> log.info(user.toString()));
    }



}
