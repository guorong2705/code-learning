package com.guorong.factory.plan_1;

import java.util.function.Supplier;

/**
 * 支付方式
 */
enum PaymentType {
    // 微信支付
    WE_CHAT(WeChatPayment::new),
    // 支付宝支付
    ALIPAY(AlipayPayment::new),
    ;
    private final Supplier<? extends IPayment> paymentSupplier;

    PaymentType(Supplier<? extends IPayment> paymentSupplier) {
        this.paymentSupplier = paymentSupplier;
    }

    /**
     * 获取支付实例
     * @return 支付实例
     */
    public IPayment getPayment() {
        return paymentSupplier.get();
    }
}
