package com.guorong.springframwork.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性集合
 */
public class PropertyValues {

    // 属性容器
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加属性
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv) {
        propertyValueList.add(pv);
    }

    /**
     * 获取属性数组
     * @return
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据属性名称获取属性值对象
     * @param propertyName 属性名称
     * @return
     */
    public PropertyValue getPropertyValue(String propertyName) {
        PropertyValue propertyValueTarget = null;
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(propertyValue)) {
                propertyValueTarget = propertyValue;
            }
        }
        return propertyValueTarget;
    }
}
