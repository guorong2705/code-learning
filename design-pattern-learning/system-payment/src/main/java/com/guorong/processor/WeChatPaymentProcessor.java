package com.guorong.processor;

import com.guorong.PaymentContext;
import com.guorong.factory.config.PaymentFactoryConfig;

/**
 * 微信支付处理器
 */
public class WeChatPaymentProcessor extends AbstractPaymentProcessor
        implements IPaymentProcessor {

    public WeChatPaymentProcessor(PaymentFactoryConfig factoryConfig, PaymentContext paymentContext) {
        super(factoryConfig, paymentContext);
    }

    @Override
    public void processPayment() {
        System.out.println(String.format("微信支付处理支付金额：%s", paymentContext.getAmount()));
    }
}
