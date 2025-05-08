package com.guorong.factory.method.plan_1;

public class AppClient {
    public static void main(String[] args) {
        IPaymentFactory factory = new WeChatPaymentFactory();
        IPayment payment = factory.createPayment();
        payment.pay(12);
        factory = new AlipayPaymentFactory();
        payment = factory.createPayment();
        payment.pay(13);
    }
}
