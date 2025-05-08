package com.guorong.notification;

import com.guorong.factory.config.PaymentFactoryConfig;

/**
 * 通知服务抽象基类
 */
public abstract class AbstractNotificationService implements INotificationService {

    protected final PaymentFactoryConfig paymentFactoryConfig;

    protected AbstractNotificationService(PaymentFactoryConfig paymentFactoryConfig) {
        this.paymentFactoryConfig = paymentFactoryConfig;
    }
}
