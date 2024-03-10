package com.guorong.strategy.v1;

class App {
    public static void main(String[] args) {
        double amount = 23.36;
        // 使用支付宝付款
        Consumer consumer = new Consumer(new AlipayPaymentStrategy());
        consumer.makePayment(amount);
        // 改用微信付款
        consumer.changeStrategy(new WechatPaymentStrategy());
        consumer.makePayment(amount);
    }
}
