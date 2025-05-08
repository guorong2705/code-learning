package com.guorong.factory.method.plan_2.factory;

import com.guorong.factory.method.plan_2.IPayment;
import com.guorong.factory.method.plan_2.PaymentContext;
import com.guorong.factory.method.plan_2.PaymentType;
import com.guorong.factory.method.plan_2.strategy.DiscountStrategyRegistry;

/**
 * 支付方式工厂接口
 */
public interface IPaymentFactory {

    /**
     * 创建支付方式
     *
     * @param context 支付上下文对象
     * @return 支付方式
     */
    IPayment getPayment(PaymentContext context);

    /**
     * 获取字符类型
     * @return 字符类型 {@link PaymentType}
     */
    PaymentType getPaymentType();

    /**
     * 切换策略注册表
     * @param strategyRegistry
     */
    void changeDiscountStrategyRegistry(DiscountStrategyRegistry strategyRegistry);

}
