package com.guorong.discount.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.guorong.utils.StringUtils;
import com.guorong.utils.ValidateUtils;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 折扣策略配置属性类
 */
public class DiscountStrategyConfig implements Serializable {

    /**
     * 折扣类型
     */
    @Getter
    private final String type;

    /**
     * 折扣优先级，值越大级别越高
     */
    @Getter
    private final Integer priority;

    /**
     * 其他配置参数
     */
    private final Map<String, Object> parameters;

    @JsonCreator
    public DiscountStrategyConfig(
            @JsonProperty("type") String type,
            @JsonProperty("priority") Integer priority,
            @JsonProperty("parameters") Map<String, Object> parameters) {
        this.type = type;
        this.priority = priority;
        this.parameters = Objects.isNull(parameters) ? new HashMap<>() : new HashMap<>(parameters);
    }

    /**
     * 验证参数
     */
    public void validate () {
        // 验证输入
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(type), "配置参数type不能为空");
        ValidateUtils.validateArgument(Objects.isNull(priority), "配置参数priority不能为null");
    }


    /**
     * 获取支付类型
     * @return 支付类型
     */
    public String getPaymentType() {
        return getParameter("paymentType", String.class);
    }

    /**
     * 获取折扣率
     * @return 折扣率
     */
    public Double getDiscountRate() {
        return getParameter("discountRate", Double.class);
    }


    /**
     * 获取会员等级
     * @return 会员等级
     */
    public String getRequiredMemberLevel() {
        return getParameter("requiredMemberLevel", String.class);
    }

    /**
     * 活动开始时间
     * @return 活动开始时间
     */
    public Integer getBeginHourOfDay() {
        return getParameter("beginHourOfDay", Integer.class);
    }

    /**
     * 活动结束时间
     * @return 活动结束时间
     */
    public Integer getEndHourOfDay() {
        return getParameter("endHourOfDay", Integer.class);
    }

    /**
     * 获取参数值
     *
     * @param key   参数key
     * @param clazz 参数值Class对象
     * @param <T>   参数值类型
     * @return 参数值
     * @throws IllegalArgumentException 参数key或clazz为空抛出
     */
    private <T> T getParameter(String key, Class<T> clazz) {
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(key), "参数key不能为空");
        ValidateUtils.validateArgument(Objects.isNull(clazz), "参数clazz不能为null");
        Object value = parameters.get(key);
        if (Objects.isNull(value)) {
            return null;
        }
        try {
            return clazz.cast(value);
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("参数类型不匹配：key=%s, 期望类型clazz=%s, 实际类型=%s",
                    key, clazz, value.getClass().getName()));
        }
    }

}
