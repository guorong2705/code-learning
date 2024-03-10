package com.guorong.strategy.v2;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

/**
 * 支付策略类型
 */
@AllArgsConstructor
enum StrategyType {
    // 支付宝
    Alipay(AlipayPaymentStrategy::new),
    //  微信
    Wechat(WechatPaymentStrategy::new),
    ;
    private final Supplier<PaymentStrategy> supplier;

    public void makePay(double amount) {
        supplier.get().pay(amount);
    }
}
