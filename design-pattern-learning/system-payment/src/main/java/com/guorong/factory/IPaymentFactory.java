package com.guorong.factory;

import com.guorong.PaymentContext;
import com.guorong.discount.IDiscountStrategy;
import com.guorong.notification.INotificationService;
import com.guorong.processor.IPaymentProcessor;

/**
 * 支付工厂接口
 */
public interface IPaymentFactory {

    /**
     * 创建支付处理器
     * @return 支付处理器
     */
    IPaymentProcessor createProcessor(PaymentContext paymentContext);

    /**
     * 创建折扣策略
     * @return 支付折扣策略
     */
    IDiscountStrategy createDiscount(PaymentContext paymentContext);


    INotificationService createNotification(PaymentContext paymentContext);
}
