package com.guorong.springframwork.beans.factory.factory;

import com.guorong.springframwork.beans.PropertyValues;

import java.util.Objects;

public class BeanDefinition {
    private Class<?> beanClass;

    // 属性集合
    private PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = Objects.isNull(propertyValues) ? new PropertyValues() : propertyValues;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    /**
     * 获取属性集合
     * @return
     */
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
