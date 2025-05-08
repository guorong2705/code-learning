package com.guorong.notification;

import com.guorong.PaymentContext;

/**
 * 通知服务接口
 */
public interface INotificationService {

    /**
     * 发送通知
     * @param message 通知信息
     * @param paymentContext  付款信息上下文
     */
    void sendNotification(String message, PaymentContext paymentContext);
}
