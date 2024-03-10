package com.guorong.mock.common;


import com.guorong.mock.dao.UserDao;
import com.guorong.mock.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class MockTest {

    private UserDao userDao;

    @BeforeEach
    public void init() {
        userDao = Mockito.mock(UserDao.class);
        initMockMethod();
    }
    public void initMockMethod() {
        // getUser() 方法
        User user1 = User.builder().name("张三").age(25).build();
        Mockito.when(userDao.getUser()).thenReturn(user1);
        // getById() 方法
        User user2 = User.builder().id(5).name("李四").age(30).build();
        Mockito.when(userDao.getById(5)).thenReturn(user2);
    }

    @Test
    public void test() {
        Assertions.assertNotNull(userDao.getUser());
    }

    @Test
    public void test02() {
        User user = userDao.getById(1);
        Assertions.assertNull(user);
        user = userDao.getById(5);
        Assertions.assertEquals(30, user.getAge());
    }

}
