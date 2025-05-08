package com.guorong.factory.plan_1;

public class AppClient {
    public static void main(String[] args) {
        // 支付宝支付
        IPayment payment = PaymentFactory.createPayment(PaymentType.ALIPAY);
        payment.pay(12);
        // 切换为微信支付
        payment = PaymentFactory.createPayment(PaymentType.WE_CHAT);
        payment.pay(20);
    }
}
