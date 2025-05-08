package com.guorong.discount;

import com.guorong.PaymentContext;
import com.guorong.enums.PaymentTypeEnum;
import org.junit.jupiter.api.Test;

class DiscountStrategyRegistryTests {

    @Test
    void testDefaultStrategy() {
        double amount = 100;
        PaymentContext context = new PaymentContext(amount, "人民币", "001", PaymentTypeEnum.ALIPAY.name());
        DiscountStrategyRegistry strategyRegistry = new DiscountStrategyRegistry("discount-strategies.json");
        IDiscountStrategy discountStrategy = strategyRegistry.selectStrategy(context);
        double discountAmount = discountStrategy.applyDiscount(amount);
        System.out.println(discountAmount);
    }
}
