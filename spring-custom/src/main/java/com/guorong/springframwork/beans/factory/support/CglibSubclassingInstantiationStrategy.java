package com.guorong.springframwork.beans.factory.support;

import com.guorong.springframwork.beans.BeansException;
import com.guorong.springframwork.beans.factory.factory.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * Cglib 实例化
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition,
                              Constructor constructor, Object[] args) throws BeansException {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        try {
            // 创建实例
            if (Objects.isNull(constructor)) {
                return enhancer.create();
            }
            return enhancer.create(constructor.getParameterTypes(), args);
        } catch (Exception e) {
            throw new BeansException(String.format("failed instantiate %s", beanClass.getName()), e);
        }
    }
}
