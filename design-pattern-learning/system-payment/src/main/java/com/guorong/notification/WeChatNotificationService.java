package com.guorong.notification;

import com.guorong.PaymentContext;
import com.guorong.factory.config.PaymentFactoryConfig;

/**
 * 微信支付通知服务
 */
public class WeChatNotificationService extends AbstractNotificationService
        implements INotificationService {

    public WeChatNotificationService(PaymentFactoryConfig paymentFactoryConfig) {
        super(paymentFactoryConfig);
    }

    @Override
    public void sendNotification(String message, PaymentContext paymentContext) {
        if (paymentFactoryConfig.getSmsEnabled()) {
            System.out.println("短信通知微信支付成功：" + message);
        }
        if (paymentFactoryConfig.getEmailEnabled()) {
            System.out.println("邮件通知微信支付成功：" + message);
        }
    }
}
