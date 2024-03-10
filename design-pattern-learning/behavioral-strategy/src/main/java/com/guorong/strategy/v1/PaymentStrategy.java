package com.guorong.strategy.v1;

/**
 * 付款策略
 */
@FunctionalInterface
interface PaymentStrategy {
    /**
     * 支付
     */
    void pay(double amount);
}

/**
 * 支付宝支付策略
 */
class AlipayPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付：" + amount + "元");
    }
}

/**
 * 微信支付策略
 */
class WechatPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付：" + amount + "元");
    }
}

