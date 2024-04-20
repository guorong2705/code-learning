package com.guorong.springframwork.beans.factory;

import com.guorong.springframwork.beans.BeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    /**
     * 获取bean实例
     * @param beanName
     * @param args 构造函数参数
     * @return bean实例
     * @throws BeansException
     */
    Object getBean(String beanName, Object... args) throws BeansException;
}
