package com.guorong.springframwork.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.guorong.springframwork.beans.BeansException;
import com.guorong.springframwork.beans.PropertyValue;
import com.guorong.springframwork.beans.PropertyValues;
import com.guorong.springframwork.beans.factory.factory.BeanDefinition;
import com.guorong.springframwork.beans.factory.factory.BeanReference;

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
            // 实例化bean
            bean = doCreateBean(beanName, beanDefinition, args);
            // 填充bean属性
            applyPropertyValues(bean, beanDefinition);
        } catch (BeansException e) {
            throw new BeansException(String.format("failed create bean instance {%s}", beanName), e);
        }
        addSingleBean(beanName, bean);
        return bean;
    }


    /**
     * 设置属性值
     * @param bean
     * @param beanDefinition
     * @throws BeansException
     */
    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws BeansException {
        try {
            // 设置bean属性
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName(); // 属性名称
                Object value = propertyValue.getValue(); // 属性值
                // 判断属性是否引用另外一个bean
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeaName());
                }
                // 填充字段属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (BeansException e) {
            throw new BeansException(String.format("error set property value"), e);
        }
    }


    /***
     * 创建bean实例
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     * @throws BeansException
     */
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition,
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

    /**
     * 获取实例化策略
     *
     * @return
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 设置实例化策略
     *
     * @param instantiationStrategy
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
