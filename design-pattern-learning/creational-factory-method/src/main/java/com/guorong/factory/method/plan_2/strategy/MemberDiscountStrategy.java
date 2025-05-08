package com.guorong.factory.method.plan_2.strategy;

import com.guorong.factory.method.plan_2.PaymentContext;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * 会员折扣策略
 */
public class MemberDiscountStrategy implements IDiscountStrategy {

    private static final Logger LOGGER = Logger.getLogger(MemberDiscountStrategy.class.getName());

    /**
     * 折扣率
     */
    private final double discountRate;

    /**
     * 会员等级
     */
    private final String requiredMemberLevel;

    /**
     * 折扣策略优先级
     */
    private final int priority;

    public MemberDiscountStrategy(double discountRate, String requiredMemberLevel, int priority) {
        this.discountRate = discountRate;
        this.requiredMemberLevel = requiredMemberLevel;
        this.priority = priority;
    }

    @Override
    public double applyDiscount(double amount) {
        double discountAmount = amount * discountRate;
        LOGGER.info(() -> String.format("应用会员折扣：会员等级=%s，原金额=%s，折扣金额=%s",
                requiredMemberLevel, amount, discountAmount));
        return discountAmount;
    }

    @Override
    public boolean isAvailable(PaymentContext context) {
        return Objects.equals(requiredMemberLevel, context.getAttribute("memberLevel", String.class));
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
