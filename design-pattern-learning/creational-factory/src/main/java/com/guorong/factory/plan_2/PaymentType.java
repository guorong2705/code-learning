package com.guorong.factory.plan_2;

import java.util.function.Supplier;

/**
 * 支付方式
 */
enum PaymentType {
    // 微信支付
    WE_CHAT(WeChatPayment::new, true),
    // 支付宝支付
    ALIPAY(AlipayPayment::new, false),
    ;
    /**
     * 支付实现类Supplier
     */
    private final Supplier<? extends IPayment> paymentSupplier;

    /**
     * 是否需要单例模式支持
     */
    private final boolean isSingleton;

    PaymentType(Supplier<? extends IPayment> paymentSupplier, boolean isSingleton) {
        this.paymentSupplier = paymentSupplier;
        this.isSingleton = isSingleton;
    }

    /**
     * 获取支付实例
     *
     * @return 支付实例
     */
    public IPayment getPayment() {
        return paymentSupplier.get();
    }

    public boolean isSingleton() {
        return isSingleton;
    }
}
