package com.guorong.factory.method.plan_2.strategy;

import com.guorong.factory.method.plan_2.PaymentContext;

/**
 * 折扣策略
 */
public interface IDiscountStrategy {
    /**
     * 应用折扣策略
     * @param amount 支付金额
     * @return 折扣后的金额
     */
    double applyDiscount(double amount);

    /**
     * 折扣策略是当前环境是否可用
     * @param context 支付上下文对象
     * @return 支持返回true,否则返回false
     */
    boolean isAvailable(PaymentContext context);

    /**
     * 折扣策略优先级，数值越小，优先级越高
     * @return 折扣策略优先级
     */
    int getPriority();
}
