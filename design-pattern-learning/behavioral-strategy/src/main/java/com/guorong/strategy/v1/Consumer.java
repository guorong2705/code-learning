package com.guorong.strategy.v1;

/**
 *  消费者
 */
class Consumer {
    /**
     * 付款策略
     */
    private PaymentStrategy strategy;

    public Consumer(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 修改付款策略
     *
     * @param strategy
     */
    public void changeStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 付款
     */
    public void makePayment(double amount) {
        strategy.pay(amount);
    }
}
