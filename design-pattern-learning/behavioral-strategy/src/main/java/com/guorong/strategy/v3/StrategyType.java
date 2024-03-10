package com.guorong.strategy.v3;

import lombok.AllArgsConstructor;

/**
 * 策略类型：使用 lambda 实现不同策略
 */
@AllArgsConstructor
enum StrategyType implements PaymentStrategy {
    // 支付宝支付
    Alipay((double amount) -> {
        System.out.println("使用支付宝支付：" + amount + "元");
    }),
    // 微信支付
    Wechat((double amount) -> {
        System.out.println("使用微信支付：" + amount + "元");
    }),
    ;
    private final PaymentStrategy strategy;

    @Override
    public void pay(double amount) {
        strategy.pay(amount);
    }
}