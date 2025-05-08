package com.guorong.factory.plan_1;

/**
 * 支付实例工厂
 */
final class PaymentFactory {
    private PaymentFactory() {
    }

    /**
     * 创建支付实例
     *
     * @param paymentType 支付类型枚举
     * @return 支付实例
     * @throws IllegalArgumentException paymentType为null
     */
    public static IPayment createPayment(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("paymentType cannot be null");
        }
        return paymentType.getPayment();
    }
}
