package com.guorong.strategy.v2;

/**
 * 消费者
 */
class Consumer {
    /**
     * 屠龙使用的战斗策略
     */
    private StrategyType strategyType;

    public Consumer(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    /**
     * 修改屠龙战斗策略
     *
     * @param strategyType
     */
    public void changeStrategy(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    /**
     * 付款
     */
    public void makePay(double amount) {
        strategyType.makePay(amount);
    }
}
