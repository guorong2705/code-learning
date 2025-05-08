package com.guorong.discount;

import com.guorong.PaymentContext;

/**
 * 支付折扣策略
 */
public interface IDiscountStrategy {

    /**
     * 引用折扣策略
     * @param amount 支付金额
     * @return 折扣优惠后的金额
     */
    double applyDiscount(double amount);

    /**
     * 获取折扣策略优先级
     * @return 策略优先级
     */
    Integer getPriority();

    boolean isAvailable(PaymentContext paymentContext);
}
