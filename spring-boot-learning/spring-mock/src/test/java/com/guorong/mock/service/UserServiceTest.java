package com.guorong.mock.service;

import com.guorong.mock.dao.UserDao;
import com.guorong.mock.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test01() {
        User mockUser = User.builder().id(10).name("张三").age(36).build();
        Mockito.when(userService.getUser()).thenReturn(mockUser);

        User user = userService.getUser();
        Assertions.assertNotNull(user);
    }

    @Test
    public void test02() {
        User mockUser = User.builder().id(15).name("李四").age(31).build();
        Mockito.when(userService.getById(15)).thenReturn(mockUser);

        User user = userService.getById(20);
        Assertions.assertNull(user);
        user = userService.getById(15);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(31, user.getAge());
    }


    @Test
    public void test03() {
        Mockito.when(userService.insertUser(Mockito.any(User.class))).thenReturn(100);

        int result = userService.insertUser(new User());
        Assertions.assertEquals(100, result);
    }

    @Test
    public void testThenThrow() {
        Mockito.when(userService.getUser()).thenThrow(new RuntimeException("爆出异常"));
        userService.getUser();
    }

}
