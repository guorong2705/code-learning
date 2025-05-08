package com.guorong.factory.plan_3;

import java.util.function.Supplier;

/**
 * 支付方式
 */
enum PaymentType {
    // 微信支付(单例)
    WE_CHAT(new SingletonSupplier<>(new WeChatPayment())),
    // 支付宝支付
    ALIPAY(AlipayPayment::new),
    ;
    /**
     * 支付实现类Supplier
     */
    private final Supplier<? extends Payment> paymentSupplier;


    PaymentType(Supplier<? extends Payment> paymentSupplier) {
        this.paymentSupplier = paymentSupplier;
    }

    /**
     * 获取支付实例
     *
     * @return 支付实例
     */
    public Payment getPayment() {
        return paymentSupplier.get();
    }
}
