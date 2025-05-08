package com.guorong.processor;

import com.guorong.PaymentContext;
import com.guorong.factory.config.PaymentFactoryConfig;

/**
 * 支付处理器抽象基类
 */
public abstract class AbstractPaymentProcessor implements IPaymentProcessor{

    /**
     * 工厂配置
     */
    protected final PaymentFactoryConfig factoryConfig;

    /**
     * 支付信息上下文
     */
    protected final PaymentContext paymentContext;

    public AbstractPaymentProcessor(PaymentFactoryConfig factoryConfig, PaymentContext paymentContext) {
        this.factoryConfig = factoryConfig;
        this.paymentContext = paymentContext;
    }
}
