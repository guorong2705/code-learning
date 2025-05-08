package com.guorong.notification;

import com.guorong.PaymentContext;
import com.guorong.factory.config.PaymentFactoryConfig;

/**
 * 支付宝通知服务
 */
public class AlipayNotificationService extends AbstractNotificationService
        implements INotificationService{


    public AlipayNotificationService(PaymentFactoryConfig paymentFactoryConfig) {
        super(paymentFactoryConfig);
    }

    @Override
    public void sendNotification(String message, PaymentContext paymentContext) {
        if (paymentFactoryConfig.getSmsEnabled()) {
            System.out.println("短信通知：支付宝支付成功:" + message + "支付金额：");
        }
        if (paymentFactoryConfig.getEmailEnabled()) {
            System.out.println("邮件通知：支付宝支付成功:" + message + "支付金额：");
        }
    }
}
