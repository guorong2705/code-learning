package com.guorong.discount;

import com.guorong.PaymentContext;
import com.guorong.utils.StringUtils;
import com.guorong.utils.ValidateUtils;

import java.util.Objects;

/**
 * 会员折扣策略
 */
public class MemberDiscountStrategy implements IDiscountStrategy {
    /**
     * 策略优先级
     */
    private final Integer priority;
    /**
     * 折扣率
     */
    private final Double discountRate;

    /**
     * 会员等级
     */
    private final String requiredMemberLevel;

    public MemberDiscountStrategy(Integer priority, Double discountRate, String requiredMemberLevel) {
        boolean invalidCondition = Objects.isNull(priority) || Objects.isNull(discountRate) || StringUtils.isStrEmpty(requiredMemberLevel);
        ValidateUtils.validateArgument(invalidCondition,
                String.format("参数不能为空: priority=%s, discountRate=%s, requiredMemberLevel=%s", priority, discountRate, requiredMemberLevel));
        this.priority = priority;
        this.discountRate = discountRate;
        this.requiredMemberLevel = requiredMemberLevel;
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
        String attributeKey = "memberLevel";
        if (!paymentContext.hasAttribute(attributeKey)) {
            return false;
        }
        String memberLevel = paymentContext.getAttribute(attributeKey, String.class);
        return Objects.equals(memberLevel, requiredMemberLevel);
    }
}
