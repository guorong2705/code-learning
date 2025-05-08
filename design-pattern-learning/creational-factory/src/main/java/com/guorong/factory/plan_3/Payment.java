package com.guorong.factory.plan_3;

/**
 * 支付方式
 */
interface Payment {
    void pay(double amount);
}

/**
 * 微信支付
 */
class WeChatPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("WeChat pay " + amount);
    }
}

/**
 * 支付宝支付
 */
class AlipayPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Alipay pay " + amount);
    }
}

/**
 * 银联支付
 */
class UnionPayPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("UnionPay pay " + amount);
    }
}















