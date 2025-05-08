package com.guorong.factory.plan_3;

import org.junit.jupiter.api.Assertions;

public class AppClient {
    public static void main(String[] args) {
        Payment weChat1 = PaymentFactory.createPayment(PaymentType.WE_CHAT);
        Payment weChat2 = PaymentFactory.createPayment(PaymentType.WE_CHAT);
        Assertions.assertSame(weChat1, weChat2);
        // 非单例
        Payment alipay1 = PaymentFactory.createPayment(PaymentType.ALIPAY);
        Payment alipay2 = PaymentFactory.createPayment(PaymentType.ALIPAY);
        Assertions.assertNotSame(alipay1, alipay2);
    }
}
