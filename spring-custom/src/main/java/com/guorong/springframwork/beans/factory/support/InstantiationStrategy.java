package com.guorong.springframwork.beans.factory.support;

import com.guorong.springframwork.beans.BeansException;
import com.guorong.springframwork.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略接口
 */
public interface InstantiationStrategy {

    /**
     * 实例化bean
     * @param beanName bean名称
     * @param beanDefinition bean定义
     * @param constructor 构造函数
     * @param args 构造函数参数
     * @return bean实例
     * @throws BeansException
     */
    Object instantiate(String beanName, BeanDefinition beanDefinition,
                       Constructor constructor, Object[] args) throws BeansException;
}
