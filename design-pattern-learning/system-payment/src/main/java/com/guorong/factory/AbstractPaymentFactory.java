package com.guorong.factory;

import com.guorong.discount.DiscountStrategyRegistry;
import com.guorong.factory.config.PaymentFactoryConfig;

/**
 * 支付工厂抽象基类
 */
public abstract class AbstractPaymentFactory implements IPaymentFactory {
    /**
     * 工厂配置信息
     */
    protected final PaymentFactoryConfig paymentFactoryConfig;

    protected final DiscountStrategyRegistry discountStrategyRegistry;

    protected AbstractPaymentFactory(PaymentFactoryConfig paymentFactoryConfig,
                                     DiscountStrategyRegistry discountStrategyRegistry) {
        this.paymentFactoryConfig = paymentFactoryConfig;
        this.discountStrategyRegistry = discountStrategyRegistry;
    }
}
