package com.guorong.strategy.v2;

class App {
    public static void main(String[] args) {
        double amount = 25.36;
        // 支付宝支付
        Consumer consumer = new Consumer(StrategyType.Alipay);
        consumer.makePay(amount);
        // 改用微信支付
        consumer.changeStrategy(StrategyType.Wechat);
        consumer.makePay(amount);
    }
}
