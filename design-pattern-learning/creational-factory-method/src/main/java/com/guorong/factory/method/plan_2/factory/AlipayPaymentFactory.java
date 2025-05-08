package com.guorong.factory.method.plan_2.factory;

import com.guorong.factory.method.plan_2.AlipayPayment;
import com.guorong.factory.method.plan_2.IPayment;
import com.guorong.factory.method.plan_2.PaymentContext;
import com.guorong.factory.method.plan_2.PaymentType;
import com.guorong.factory.method.plan_2.strategy.DiscountStrategyRegistry;
import com.guorong.factory.method.plan_2.strategy.IDiscountStrategy;

import java.util.logging.Logger;

/**
 * 支付宝支付创建工厂类
 */
public class AlipayPaymentFactory extends AbstractPaymentFactory {
    private static final Logger LOGGER = Logger.getLogger(AlipayPaymentFactory.class.getName());

    public AlipayPaymentFactory() {
    }

    @Override
    public IPayment getPayment(PaymentContext context) {
        LOGGER.info(() -> String.format("创建支付宝支付，环境=%s", context.getEnvironment()));
        IDiscountStrategy strategy = selectStrategy(context);
        return new AlipayPayment(strategy);
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.ALIPAY;
    }
}
