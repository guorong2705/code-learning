package com.guorong.springframwork;

import com.guorong.springframwork.bean.UserDao;
import com.guorong.springframwork.bean.UserService;
import com.guorong.springframwork.beans.BeansException;
import com.guorong.springframwork.beans.PropertyValue;
import com.guorong.springframwork.beans.PropertyValues;
import com.guorong.springframwork.beans.factory.factory.BeanDefinition;
import com.guorong.springframwork.beans.factory.factory.BeanReference;
import com.guorong.springframwork.beans.factory.support.DefaultListableBeanFactory;
import com.guorong.springframwork.beans.factory.support.SimpleInstantiationStrategy;
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
        UserService userService = (UserService) beanFactory.getBean(beanName, "u001");
        userService.printUid();
    }

    // 测试属性填充
    @Test
    public void testPropertyValues() throws BeansException {
        // 注册UserDao
        BeanDefinition daoBeanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition(UserDao.class.getName(), daoBeanDefinition);
        // 创建属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "u001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference(UserDao.class.getName())));
        // 注册UserService
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition(UserService.class.getName(), userServiceBeanDefinition);
        // 获取UserService实例
        UserService userService = (UserService) beanFactory.getBean(UserService.class.getName());
        userService.queryUserInfo();
    }


}
