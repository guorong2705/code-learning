package com.guorong.strategy.v3;

class App {
    public static void main(String[] args) {
        double amount = 65.36;
        // 支付宝支付
        Consumer slayer = new Consumer(StrategyType.Alipay);
        slayer.makePay(amount);
        // 改用微信支付
        slayer.changeStrategy(StrategyType.Wechat);
        slayer.makePay(amount);
    }
}
