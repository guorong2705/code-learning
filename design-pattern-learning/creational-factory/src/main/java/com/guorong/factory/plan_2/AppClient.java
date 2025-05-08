package com.guorong.factory.plan_2;

import org.junit.jupiter.api.Assertions;

public class AppClient {
    public static void main(String[] args) {
        IPayment weChatPay1 = PaymentFactory.createPayment(PaymentType.WE_CHAT);
        IPayment weChatPay2 = PaymentFactory.createPayment(PaymentType.WE_CHAT);
        if (weChatPay1 == weChatPay2) {
            System.out.println("weChatPay1==weChatPay2");
        }
        // 非单例
        IPayment alipay1 = PaymentFactory.createPayment(PaymentType.ALIPAY);
        IPayment alipay2 = PaymentFactory.createPayment(PaymentType.ALIPAY);
        if (alipay1 != alipay2) {
            System.out.println("alipay1 != alipay2");
        }
    }
}
