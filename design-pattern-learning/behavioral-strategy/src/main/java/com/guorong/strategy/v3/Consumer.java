package com.guorong.strategy.v3;

/**
 * 消费者
 */
class Consumer {
    /**
     * 支付类型
     */
    private StrategyType strategyType;

    public Consumer(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    /**
     * 修改支付策略
     *
     * @param strategyType
     */
    public void changeStrategy(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    /**
     * 去战斗
     */
    public void makePay(double amount) {
        strategyType.pay(amount);
    }
}
