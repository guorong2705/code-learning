package com.guorong.factory.method.plan_2.factory;

import com.guorong.factory.method.plan_2.PaymentContext;
import com.guorong.factory.method.plan_2.strategy.DiscountStrategyRegistry;
import com.guorong.factory.method.plan_2.strategy.IDiscountStrategy;
import com.guorong.factory.method.plan_2.strategy.NoDiscountStrategy;

import java.util.List;
import java.util.logging.Logger;

/**
 * 支付工厂抽象基类
 */
public abstract class AbstractPaymentFactory implements IPaymentFactory {
    private static final Logger LOGGER = Logger.getLogger(AbstractPaymentFactory.class.getName());

    /**
     * 折扣策略注册表
     */
    private DiscountStrategyRegistry discountStrategyRegistry;

    protected AbstractPaymentFactory() {

    }

    public void changeDiscountStrategyRegistry(DiscountStrategyRegistry strategyRegistry) {
        this.discountStrategyRegistry = strategyRegistry;
    }


    /**
     * 根据支付上下信息创建正确的支付策略
     *
     * @param context 支付上下文对象
     * @return 支付策略
     */
    protected IDiscountStrategy selectStrategy(PaymentContext context) {
        // 折扣策略注册表不存在，直接使用默认无折扣
        if (discountStrategyRegistry == null) {
            return new NoDiscountStrategy();
        }
        IDiscountStrategy selected = null;
        int minPriority = Integer.MAX_VALUE;
        // 获取注册表中的折扣策略
        List<IDiscountStrategy> strategies = discountStrategyRegistry.getStrategies();
        // 根据规则选出符合当前的折扣策略
        for (IDiscountStrategy strategy : strategies) {
            if (strategy.isAvailable(context) && minPriority >= strategy.getPriority()) {
                selected = strategy;
                minPriority = strategy.getPriority();
            }
        }
        if (selected == null) {
            LOGGER.severe(() -> String.format("没有找到可用的策略：paymentContext=%s", context));
            throw new IllegalStateException("没有找到策略");
        }
        return selected;
    }
}
