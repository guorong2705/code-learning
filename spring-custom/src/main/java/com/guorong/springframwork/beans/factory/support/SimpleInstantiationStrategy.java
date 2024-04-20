package com.guorong.springframwork.beans.factory.support;

import com.guorong.springframwork.beans.BeansException;
import com.guorong.springframwork.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * JDK 实例化
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition,
                              Constructor constructor, Object[] args) throws BeansException {
        Class<?> beanClass = beanDefinition.getBeanClass();
        try {

            // 使用无参构造函数实例化
            if (Objects.isNull(constructor)) {
                return beanClass.getDeclaredConstructor().newInstance();
            }

            // 使用传入的构造函数实例化
            return beanClass.getDeclaredConstructor(constructor.getParameterTypes())
                    .newInstance(args);

        } catch (InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException(String.format("fail instantiate %s", beanClass.getName()), e);
        }
    }
}
