package com.guorong.factory;

import com.guorong.discount.DiscountStrategyRegistry;
import com.guorong.factory.config.PaymentFactoryConfig;
import com.guorong.factory.config.PaymentFactoryConfigParser;
import com.guorong.utils.StringUtils;
import com.guorong.utils.ValidateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * 支付工厂注册表
 */
public class PaymentFactoryRegistry {

    private static final Logger LOGGER = Logger.getLogger(PaymentFactoryRegistry.class.getName());
    /**
     * 支付工厂缓存
     */
    private final Map<String, IPaymentFactory> factoryMap;

    private final DiscountStrategyRegistry discountStrategyRegistry;

    public PaymentFactoryRegistry(DiscountStrategyRegistry discountStrategyRegistry, String configPath) {
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(configPath),
                "configPath不能为空");
        ValidateUtils.validateArgument(Objects.isNull(discountStrategyRegistry),
                "strategyRegistry不能为空");
        // 折扣策略注册表
        this.discountStrategyRegistry = discountStrategyRegistry;
        // 注册支付工厂
        List<PaymentFactoryConfig> factoryConfigs =
                new PaymentFactoryConfigParser(configPath).loadConfig();
        this.factoryMap = parseFactoryConfigs(factoryConfigs);
    }

    /**
     * 获取支付工厂
     *
     * @param paymentType 支付类型
     * @return 支付工厂实例
     */
    public IPaymentFactory getPaymentFactory(String paymentType) {
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(paymentType),
                "参数paymentType不能为空");
        IPaymentFactory paymentFactory = factoryMap.get(paymentType);
        if (Objects.isNull(paymentFactory)) {
            throw new IllegalArgumentException(String.format("未找到工厂：paymentType=%s", paymentType));
        }
        return paymentFactory;
    }

    /**
     * 将工厂配置解析为工厂类
     *
     * @param factoryConfigs 工厂配置类集合
     */
    private Map<String, IPaymentFactory> parseFactoryConfigs(List<PaymentFactoryConfig> factoryConfigs) {
        Map<String, IPaymentFactory> map = new HashMap<>();
        if (Objects.isNull(factoryConfigs) || factoryConfigs.isEmpty()) {
            return map;
        }
        for (PaymentFactoryConfig factoryConfig : factoryConfigs) {
            IPaymentFactory paymentFactory = parseFactoryConfigs(factoryConfig);
            LOGGER.info(() -> String.format("注册支付工厂：%s", paymentFactory.getClass().getSimpleName()));
            map.put(factoryConfig.getPaymentType(), paymentFactory);
        }
        return map;
    }

    /**
     * 解析支付工厂配置
     *
     * @param factoryConfig 工厂配置类
     * @return 支付工厂实例
     */
    private IPaymentFactory parseFactoryConfigs(PaymentFactoryConfig factoryConfig) {
        ValidateUtils.validateArgument(Objects.isNull(factoryConfig), "config不能null");
        switch (factoryConfig.getPaymentType()) {
            case "ALIPAY":
                return new AlipayPaymentFactory(factoryConfig, discountStrategyRegistry);
            case "WECHAT":
                return new WeChatPaymentFactory(factoryConfig, discountStrategyRegistry);
            default:
                throw new IllegalArgumentException("未找到匹配支付类型");
        }
    }


}
