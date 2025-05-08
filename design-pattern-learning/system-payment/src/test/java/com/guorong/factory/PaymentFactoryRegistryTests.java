package com.guorong.factory;

import com.guorong.PaymentContext;
import com.guorong.discount.DiscountStrategyRegistry;
import com.guorong.enums.PaymentTypeEnum;
import com.guorong.processor.IPaymentProcessor;
import org.junit.jupiter.api.Test;

 class PaymentFactoryRegistryTests {

    @Test
    void method() {
        String factoryConfigPath = "payment-factories.json";
        String discountConfigPath = "discount-strategies.json";
        PaymentContext context = new PaymentContext(100, "人民币", "001",
                PaymentTypeEnum.ALIPAY.name());
        DiscountStrategyRegistry strategyRegistry = new DiscountStrategyRegistry(discountConfigPath);
        PaymentFactoryRegistry factory = new PaymentFactoryRegistry(strategyRegistry, factoryConfigPath);
        IPaymentFactory paymentFactory = factory.getPaymentFactory(context.getPaymentType());
        IPaymentProcessor processor = paymentFactory.createProcessor(context);
        processor.processPayment();

    }
}
