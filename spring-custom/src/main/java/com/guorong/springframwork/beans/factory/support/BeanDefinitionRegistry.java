package com.guorong.springframwork.beans.factory.support;

import com.guorong.springframwork.beans.factory.factory.BeanDefinition;

/**
 * bean定义注册接口
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
