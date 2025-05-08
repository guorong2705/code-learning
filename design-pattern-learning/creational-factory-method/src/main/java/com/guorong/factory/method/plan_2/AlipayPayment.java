package com.guorong.factory.method.plan_2;

import com.guorong.factory.method.plan_2.strategy.IDiscountStrategy;

import java.util.logging.Logger;

/**
 * 支付宝支付
 */
public class AlipayPayment implements IPayment {
    private static final Logger LOGGER = Logger.getLogger(AlipayPayment.class.getName());
    /**
     * 支付策略
     */
    private final IDiscountStrategy paymentStrategy;

    public AlipayPayment(IDiscountStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public void pay(PaymentContext context) {
        if (!paymentStrategy.isAvailable(context)) {
            LOGGER.severe(() -> String.format("支付包支付不可用，环境=%s", context.getEnvironment()));
            throw new IllegalStateException("支付包支付不可用");
        }
        // 获取折扣金额
        double discountAmount = paymentStrategy.applyDiscount(context.getAmount());
        LOGGER.info(() -> String.format("支付宝支付处理：环境=%s, 金额=%s", context.getEnvironment(), discountAmount));
        System.out.println("支付宝支付金额：" + discountAmount);
    }
}
