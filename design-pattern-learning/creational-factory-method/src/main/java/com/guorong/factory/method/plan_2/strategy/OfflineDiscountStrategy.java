package com.guorong.factory.method.plan_2.strategy;

import com.guorong.factory.method.plan_2.EnvironmentType;
import com.guorong.factory.method.plan_2.PaymentContext;

import java.util.logging.Logger;

/**
 * 线下折扣策略
 */
public class OfflineDiscountStrategy implements IDiscountStrategy {

    private static final Logger LOGGER = Logger.getLogger(OfflineDiscountStrategy.class.getName());

    /**
     * 折扣率
     */
    private final double discountRate;

    /**
     * 优先级
     */
    private final int priority;

    public OfflineDiscountStrategy(double discountRate, int priority) {
        this.discountRate = discountRate;
        this.priority = priority;
    }

    @Override
    public double applyDiscount(double amount) {
        double discountAmount = amount * discountRate;
        LOGGER.info(() -> String.format("线下折扣策略：原金额=%s, 折扣后金额=%s", amount, discountAmount));
        return discountAmount;
    }

    @Override
    public boolean isAvailable(PaymentContext context) {
        return EnvironmentType.OFFLINE == context.getEnvironment();
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
