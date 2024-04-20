package com.guorong.springframwork.beans.factory.support;

import com.guorong.springframwork.beans.factory.factory.SingleBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例 bean 注册中心默认实现
 */
public class DefaultSingletonBeanRegistry implements SingleBeanRegistry {

    private Map<String, Object> singleObjects = new HashMap<>();

    @Override
    public Object getSingleBean(String beanName) {
        return singleObjects.get(beanName);
    }

    protected void addSingleBean(String beanName, Object singleBean) {
        singleObjects.put(beanName, singleBean);
    }
}
