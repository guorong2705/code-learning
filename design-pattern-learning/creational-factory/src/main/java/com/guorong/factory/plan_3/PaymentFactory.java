package com.guorong.factory.plan_3;


final class PaymentFactory {
    private PaymentFactory() {
    }


    /**
     * 创建支付方式
     *
     * @param paymentType 支付方式枚举
     * @return 支付方式实例
     * @throws IllegalArgumentException 方法参数为null
     */
    public static Payment createPayment(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("paymentType cannot be null");
        }
        return paymentType.getPayment();
    }

}
