package com.guorong.factory.method.plan_2.factory;

import com.guorong.factory.method.plan_2.PaymentType;

/**
 * 支付工厂注册中心
 */
public interface IPaymentFactoryRegistry {

    /**
     * 获取支付工厂
     * @param paymentType 支付类型 {@link PaymentType}
     * @return 支付工厂
     */
    IPaymentFactory getPaymentFactory(PaymentType paymentType);

    /**
     * 注册支付工厂
     * @param paymentType 支付类型 {@link PaymentType}
     * @param paymentFactory 支付工厂
     */
    void register(PaymentType paymentType, IPaymentFactory paymentFactory);
}
