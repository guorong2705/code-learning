package com.guorong.discount;

import com.guorong.PaymentContext;
import com.guorong.utils.StringUtils;
import com.guorong.utils.ValidateUtils;

import java.util.Objects;

/**
 * 默认折扣策略
 */
public class DefaultDiscountStrategy implements IDiscountStrategy {
    /**
     * 优先级
     */
    private final Integer priority;

    /**
     * 折扣率
     */
    private final Double discountRate;

    /**
     * 支付类型
     */
    private final String paymentType;

    public DefaultDiscountStrategy(Integer priority,
                                   Double discountRate,
                                   String paymentType) {
        boolean validate = Objects.isNull(priority) || Objects.isNull(discountRate) || StringUtils.isStrEmpty(paymentType);
        ValidateUtils.validateArgument(validate,
                String.format("参数不能为空：priority=%s, discountRate=%s, paymentType=%s", priority, discountRate, paymentType));
        this.priority = priority;
        this.discountRate = discountRate;
        this.paymentType = paymentType;
    }

    @Override
    public double applyDiscount(double amount) {
        return amount * (1 - discountRate);
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    @Override
    public boolean isAvailable(PaymentContext paymentContext) {
        return Objects.equals(paymentContext.getPaymentType(), paymentType);
    }
}
