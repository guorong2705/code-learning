package com.guorong.factory.method.plan_2.factory;

import com.guorong.factory.method.plan_2.PaymentType;
import com.guorong.factory.method.plan_2.strategy.DiscountStrategyRegistry;

import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

/**
 * 默认支付工厂注册中心: 提供SPI动态注册机制
 */
public class DefaultPaymentFactoryRegistry implements IPaymentFactoryRegistry {

    private static final Logger LOGGER = Logger.getLogger(DefaultPaymentFactoryRegistry.class.getName());
    /**
     * 支付工厂缓存
     */
    private final ConcurrentMap<PaymentType, IPaymentFactory> factoryCache;

    /**
     * 折扣策略注册表
     */
    private final DiscountStrategyRegistry discountStrategyRegistry;

    /**
     * 实例化 默认支付工厂注册中心
     * @param discountStrategyConfigPath 折扣策略配置文件路径
     */
    public DefaultPaymentFactoryRegistry(String discountStrategyConfigPath) {
        this.factoryCache = new ConcurrentHashMap<>();
        this.discountStrategyRegistry = new DiscountStrategyRegistry(discountStrategyConfigPath);
        // 注册SPI的支付工厂服务
        registerSpiPaymentFactory();
    }

    @Override
    public IPaymentFactory getPaymentFactory(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("paymentType 不能为null");
        }
        IPaymentFactory factory = factoryCache.get(paymentType);
        if (factory == null) {
            LOGGER.severe(() -> String.format("支付工厂不存在：paymentType=%s", paymentType));
            throw new IllegalArgumentException(String.format("没有找到支付工厂：paymentType=%s", paymentType));
        }
        return factory;
    }

    @Override
    public void register(PaymentType paymentType, IPaymentFactory paymentFactory) {
        if (paymentType == null) {
            throw new IllegalArgumentException("payment 不能为 null");
        }
        if (paymentFactory == null) {
            throw new IllegalArgumentException("paymentFactory 不能为 null");
        }
        if (factoryCache.containsKey(paymentType)) {
            LOGGER.severe(() -> String.format("重复注册：paymentType=%s, paymentFactory=%s", paymentType, paymentFactory));
            throw new IllegalArgumentException(String.format("重复注册: paymentType=%s", paymentType));
        }
        // 注册了策略模式

        factoryCache.put(paymentType, paymentFactory);
        LOGGER.info(() -> String.format("成功注册支付工厂：paymentType=%s, factory=%s", paymentType, paymentFactory));
    }

    /**
     * SPI自动注册工厂
     */
    private void registerSpiPaymentFactory() {
        // 获取SPI注册的服务
        ServiceLoader<IPaymentFactory> serviceLoader = ServiceLoader.load(IPaymentFactory.class);
        for (IPaymentFactory factory : serviceLoader) {
            PaymentType paymentType = factory.getPaymentType();
            // 设置折扣策略注册表
            factory.changeDiscountStrategyRegistry(discountStrategyRegistry);
            register(paymentType, factory);
        }
    }
}
