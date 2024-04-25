package com.guorong.springframwork.beans.factory.support;

import com.guorong.springframwork.beans.BeansException;
import com.guorong.springframwork.beans.factory.BeanFactory;
import com.guorong.springframwork.beans.factory.factory.BeanDefinition;

import java.util.Objects;

/**
 * 抽象bean工厂
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBean(beanName, new Object[0]);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        Object bean = getSingleBean(beanName);
        if (Objects.nonNull(bean)) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        bean = createBean(beanName, beanDefinition, args);
        return bean;
    }

    // 创建bean实例
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,
                                         Object[] args) throws BeansException;

    // 获取 bean定义
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


}
