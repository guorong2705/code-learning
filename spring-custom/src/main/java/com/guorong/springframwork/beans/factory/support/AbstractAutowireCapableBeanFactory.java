package com.guorong.springframwork.beans.factory.support;

import com.guorong.springframwork.beans.BeansException;
import com.guorong.springframwork.beans.factory.BeanFactory;
import com.guorong.springframwork.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;
import java.util.Objects;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    // 实例化策略
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,
                                Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (BeansException e) {
            throw new BeansException(String.format("failed create bean instance {%s}", beanName), e);
        }
        addSingleBean(beanName, bean);
        return bean;
    }

    // 创建bean实例
    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition,
                                        Object[] args) throws BeansException {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 获取构造参数的构造函数
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            if (Objects.nonNull(args) && ctor.getParameterCount() == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanName, beanDefinition, constructorToUse, args);
    }
}
