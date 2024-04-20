package com.guorong.springframwork.beans.factory.factory;

/**
 * 单例bean注册中心
 */
public interface SingleBeanRegistry {
    Object getSingleBean(String beanName);
}
