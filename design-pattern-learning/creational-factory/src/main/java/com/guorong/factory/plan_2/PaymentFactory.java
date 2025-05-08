package com.guorong.factory.plan_2;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 支付类型工厂
 */
final class PaymentFactory {

    /**
     * 支付单例缓存
     */
    private static final ConcurrentMap<PaymentType, IPayment> PAYMENT_SINGLETON_CACHE = new ConcurrentHashMap<>();

    private PaymentFactory() {
    }


    /**
     * 创建支付方式
     *
     * @param paymentType 支付方式枚举
     * @return 支付方式实例
     * @throws IllegalArgumentException 方法参数为null
     */
    public static IPayment createPayment(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("paymentType cannot be null");
        }
        if (paymentType.isSingleton()) {
            return PAYMENT_SINGLETON_CACHE.computeIfAbsent(paymentType, PaymentType::getPayment);
        }
        return paymentType.getPayment();
    }

}
