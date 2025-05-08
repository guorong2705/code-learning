package com.guorong.factory;

import com.guorong.PaymentContext;
import com.guorong.discount.DiscountStrategyRegistry;
import com.guorong.discount.IDiscountStrategy;
import com.guorong.factory.config.PaymentFactoryConfig;
import com.guorong.notification.AlipayNotificationService;
import com.guorong.notification.INotificationService;
import com.guorong.processor.AlipayPaymentProcessor;
import com.guorong.processor.IPaymentProcessor;

/**
 * 支付宝支付工厂
 */
public class AlipayPaymentFactory extends AbstractPaymentFactory
        implements IPaymentFactory {

    public AlipayPaymentFactory(PaymentFactoryConfig config,
                                DiscountStrategyRegistry discountStrategyRegistry) {
        super(config, discountStrategyRegistry);
    }

    @Override
    public IPaymentProcessor createProcessor(PaymentContext paymentContext) {
        return new AlipayPaymentProcessor(paymentFactoryConfig, paymentContext);
    }

    @Override
    public IDiscountStrategy createDiscount(PaymentContext paymentContext) {
        return discountStrategyRegistry.selectStrategy(paymentContext);
    }

    @Override
    public INotificationService createNotification(PaymentContext paymentContext) {
        return new AlipayNotificationService(paymentFactoryConfig);
    }
}
