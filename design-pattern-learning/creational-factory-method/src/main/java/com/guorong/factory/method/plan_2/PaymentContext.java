package com.guorong.factory.method.plan_2;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 支付上下文
 */
public class PaymentContext {
    private static final Logger LOGGER = Logger.getLogger(PaymentContext.class.getName());
    // 支付金额
    @Getter
    private final double amount;
    // 环境类型
    @Getter
    private final IEnvironmentType environment;
    // 扩展属性
    private final Map<String, Object> attributes;

    public PaymentContext(double amount, IEnvironmentType environment) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount不能为负数");
        }
        if (environment == null) {
            throw new IllegalArgumentException("environment不能为null");
        }
        this.amount = amount;
        this.environment = environment;
        this.attributes = new HashMap<>();
    }

    /**
     * 添加属性
     *
     * @param key   属性key
     * @param value 属性值
     * @return 返回当前对象
     */
    public PaymentContext addAttribute(String key, Object value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException(String.format("无效参数：key=%s, value=%s", key, value));
        }
        attributes.put(key, value);
        return this;
    }

    /**
     * 判断是否存在属性
     * @param key 属性key
     * @return 存在属性返回true，否则返回false
     */
    public boolean hasAttribute(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("key参数不能为空");
        }
        return attributes.containsKey(key);
    }

    /**
     * 获取属性值
     *
     * @param key 属性key
     * @return 属性值
     */
    public <T> T getAttribute(String key, Class<T> type) {
        if (key == null || key.trim().isEmpty() || type == null) {
            throw new IllegalArgumentException(String.format("参数无效：key=%s, type=%s", key, type));
        }
        Object value = attributes.get(key);
        if (value == null) {
            LOGGER.warning(() -> String.format("没有发现属性：key=%s", key));
            return null;
        }
        try {
            return type.cast(value);
        } catch (ClassCastException e) {
            LOGGER.severe(() -> String.format("属性值和类型不匹配: type=%s, key=%s, value=%s", type, key, value));
            throw new IllegalArgumentException(String.format("属性类型是无效的：key=%s", key), e);
        }
    }
}
