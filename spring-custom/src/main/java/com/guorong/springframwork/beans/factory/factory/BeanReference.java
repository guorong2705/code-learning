package com.guorong.springframwork.beans.factory.factory;

/**
 * 类引用
 */
public class BeanReference {
    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeaName() {
        return beanName;
    }
}
