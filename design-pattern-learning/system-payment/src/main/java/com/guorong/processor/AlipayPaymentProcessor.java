package com.guorong.processor;

import com.guorong.PaymentContext;
import com.guorong.factory.config.PaymentFactoryConfig;

/**
 * 支付宝支付处理器
 */
public class AlipayPaymentProcessor extends AbstractPaymentProcessor
        implements IPaymentProcessor {


    public AlipayPaymentProcessor(PaymentFactoryConfig factoryConfig,
                                  PaymentContext paymentContext) {
        super(factoryConfig, paymentContext);
    }

    @Override
    public void processPayment() {
        System.out.println(String.format("支付宝处理支付金额：%s", paymentContext.getAmount()));
    }
}
