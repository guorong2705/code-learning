package com.guorong.factory.method.plan_2.strategy;

import com.guorong.factory.method.plan_2.PaymentContext;

/**
 * 默认无折扣策略,兜底使用
 */
public class NoDiscountStrategy implements IDiscountStrategy{
    @Override
    public double applyDiscount(double amount) {
        return amount;
    }

    @Override
    public boolean isAvailable(PaymentContext context) {
        return true;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
