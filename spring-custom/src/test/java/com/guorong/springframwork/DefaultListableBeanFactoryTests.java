package com.guorong.springframwork;

import com.guorong.springframwork.bean.UserService;
import com.guorong.springframwork.beans.BeansException;
import com.guorong.springframwork.beans.factory.factory.BeanDefinition;
import com.guorong.springframwork.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DefaultListableBeanFactoryTests {

    private static DefaultListableBeanFactory beanFactory;

    @BeforeAll
    public static void init() {
        beanFactory = new DefaultListableBeanFactory();
    }

    // 使用无参数构造函数创建实例
    @Test
    public void testDefaultConstructor() throws BeansException {
        String beanName = UserService.class.getName();
        // 注册bean定义
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
        // 获取bean
        UserService userService = (UserService) beanFactory.getBean(beanName);
        userService.queryUserInfo();
    }

    @Test
    public void testConstructor() throws BeansException {
        String beanName = UserService.class.getName();
        // 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
        // 获取bean
        UserService userService = (UserService) beanFactory.getBean(beanName, "张三");
        userService.queryUserInfo();
    }


}
