package com.guorong.factory.plan_1;

/**
 * 支付方式
 */
interface IPayment {
    void pay(double amount);
}

/**
 * 微信支付
 */
class WeChatPayment implements IPayment {
    @Override
    public void pay(double amount) {
        System.out.println("WeChat pay " + amount);
    }
}

/**
 * 支付宝支付
 */
class AlipayPayment implements IPayment {

    @Override
    public void pay(double amount) {
        System.out.println("Alipay pay " + amount);
    }
}















