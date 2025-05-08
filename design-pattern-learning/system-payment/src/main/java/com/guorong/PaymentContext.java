package com.guorong;

import com.guorong.utils.StringUtils;
import com.guorong.utils.ValidateUtils;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 支付信息
 */
public class PaymentContext implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(PaymentContext.class.getName());

    /**
     * 支付金额
     */
    @Getter
    private final double amount;

    /**
     * 货币
     */
    @Getter
    private final String currency;
    /**
     * 支付用户id
     */
    @Getter
    private final String userId;

    /**
     * 支付方式
     */
    @Getter
    private final String paymentType;

    /**
     * 其他信息
     */
    private final HashMap<String, Object> attributes;

    public PaymentContext(double amount, String currency,
                          String userId, String paymentType) {
        // 校验金额
        ValidateUtils.validateArgument(amount < 0,
                "支付金额参数amount不能小于0");
        // 校验货币
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(currency),
                "支付货币参数currency不能为空");
        // 校验用户id
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(userId),
                "用户编号参数userId 不能为空");
        // 支付类型
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(paymentType),
                "支付类型参数paymentType不能为空");
        this.amount = amount;
        this.currency = currency;
        this.userId = userId;
        this.paymentType = paymentType;
        this.attributes = new HashMap<>();
    }

    /**
     * 添加属性键值对, 如果key重复，将会覆盖之前的值
     *
     * @param key   属性key
     * @param value 属性value
     * @throws IllegalArgumentException 如果key或者value为空抛出
     */
    public PaymentContext addAttribute(String key, Object value) {
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(key) || Objects.isNull(value),
                String.format("参数key和value不能为空:key=%s, value=%s", key, value));
        // 覆盖之前属性如果存在
        attributes.put(key, value);
        LOGGER.info(() -> String.format("添加属性成功：key=%s, value=%s", key, value));
        return this;
    }

    /**
     * 判断是否存在key属性
     *
     * @param key 查找属性key
     * @return 如果存在属性，返回true，否则返回false
     * @throws IllegalArgumentException 参数key为空抛出
     */
    public boolean hasAttribute(String key) {
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(key), "参数key不能为空");
        return attributes.containsKey(key);
    }

    /**
     * 获取属性值
     *
     * @param key   属性key
     * @param clazz 属性value类型Class实例
     * @param <T>   属性value类型
     * @return 属性value值
     * @throws IllegalArgumentException 参数key或clazz为空抛出
     * @throws ClassCastException value值类型和clazz不匹配转换失败抛出
     */
    public <T> T getAttribute(String key, Class<T> clazz) {
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(key) || Objects.isNull(clazz),
                String.format("参数不能为空: key=%s, clazz=%s", key, clazz));
        Object value = attributes.get(key);
        if (Objects.isNull(value)) {
            LOGGER.info(() -> String.format("获取属性不存在: key=%s, clazz=%s", key, clazz.getName()));
            return null;
        }
        // 转换类型
        try {
            return clazz.cast(value);
        } catch (ClassCastException e) {
            LOGGER.severe(() -> String.format("转换类型错误：key=%s, 期望类型=%s, 值类型=%s",
                    key, clazz.getName(), value.getClass().getName()));

            throw new ClassCastException(String.format("转换类型错误：key=%s, 期望类型=%s, 值类型=%s",
                            key, clazz.getName(), value.getClass().getName()));
        }
    }

}
