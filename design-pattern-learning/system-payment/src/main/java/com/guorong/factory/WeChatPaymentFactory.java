package com.guorong.factory;

import com.guorong.PaymentContext;
import com.guorong.discount.DiscountStrategyRegistry;
import com.guorong.factory.config.PaymentFactoryConfig;
import com.guorong.notification.INotificationService;
import com.guorong.notification.WeChatNotificationService;
import com.guorong.processor.IPaymentProcessor;
import com.guorong.discount.IDiscountStrategy;
import com.guorong.processor.WeChatPaymentProcessor;

/**
 * 微信支付
 */
public class WeChatPaymentFactory extends AbstractPaymentFactory
        implements IPaymentFactory {

    public WeChatPaymentFactory(PaymentFactoryConfig paymentFactoryConfig, DiscountStrategyRegistry discountStrategyRegistry) {
        super(paymentFactoryConfig, discountStrategyRegistry);
    }

    @Override
    public IPaymentProcessor createProcessor(PaymentContext paymentContext) {
        return new WeChatPaymentProcessor(paymentFactoryConfig, paymentContext);
    }

    @Override
    public IDiscountStrategy createDiscount(PaymentContext paymentContext) {
        return discountStrategyRegistry.selectStrategy(paymentContext);
    }

    @Override
    public INotificationService createNotification(PaymentContext paymentContext) {
        return new WeChatNotificationService(paymentFactoryConfig);
    }
}
