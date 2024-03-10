package com.guorong.strategy.v3;

/**
 * 支付策略
 */
@FunctionalInterface
interface PaymentStrategy {
    // 支付
    void pay(double amount);
}

