package com.guorong.factory.method.plan_2;

/**
 * 支付方式
 */
public interface IPayment {
    /**
     * 支付
     * @param context 支付上下文
     */
    void pay(PaymentContext context);
}

