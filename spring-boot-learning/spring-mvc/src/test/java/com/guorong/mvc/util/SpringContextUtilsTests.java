package com.guorong.mvc.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootTest
public class SpringContextUtilsTests {

    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    @BeforeEach
    public void init() {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(Object.class)
                .getBeanDefinition();
        defaultListableBeanFactory.registerBeanDefinition("testBean", beanDefinition);
    }

    @Test
    public void test01() {
        Object testBean = SpringContextUtils.getBean("testBean");
        log.info("bean name --->>> {}", testBean.getClass().getName());
    }



}
