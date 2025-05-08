package com.guorong.factory.method.plan_2.strategy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 策略模式配置
 */
public class DiscountStrategyConfig implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(DiscountStrategyConfig.class.getName());

    /**
     * 策略类型
     */

    @Getter
    private final String type;

    /**
     * 策略优先级
     */
    @Getter
    private final int priority;

    /**
     * 策略参数，键值对形式存储实际参数
     */
    private final Map<String, Object> parameters;

    @JsonCreator
    public DiscountStrategyConfig(
            @JsonProperty("type") String type,
            @JsonProperty("priority") int priority,
            @JsonProperty("parameters") Map<String, Object> parameters) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("参数type不能为空");
        }
        if (priority < 0) {
            throw new IllegalArgumentException("参数priority不能为负数");
        }
        this.type = type;
        this.priority = priority;
        this.parameters = parameters != null ? new HashMap<>(parameters) : new HashMap<>();
    }

    /**
     * 获取折扣率
     *
     * @return 折扣率值
     */
    @JsonProperty()
    public Double getDiscountRate() {
        return getParameter("discountRate", Double.class);
    }

    /**
     * 获取会员等级
     *
     * @return 会员等级
     */
    public String getMemberLevel() {
        return getParameter("requiredMemberLevel", String.class);
    }

    /**
     * 获取参数值
     *
     * @param key  参数字段名
     * @param type 参数值类型
     * @param <T>  参数值类型
     * @return 参数值
     */
    private <T> T getParameter(String key, Class<T> type) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("参数key不能为空");
        }
        if (type == null) {
            throw new IllegalArgumentException("参数type不能为null");
        }
        Object value = parameters.get(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format("没有找到参数值：key=%s", key));
        }
        try {
            return type.cast(value);
        } catch (ClassCastException e) {
            LOGGER.severe(() -> String.format("参数类型不匹配：key=%s, 当前值类型=%s, 期望值类型=%s",
                    key, value.getClass().getSimpleName(), type.getSimpleName()));

            throw new IllegalArgumentException(String.format("参数类型不匹配：key=%s, 当前值类型=%s, 期望值类型=%s",
                    key, value.getClass().getSimpleName(), type.getSimpleName()));
        }
    }

}
