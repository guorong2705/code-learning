package com.guorong.discount;

import com.guorong.PaymentContext;
import com.guorong.discount.config.DiscountStrategyConfig;
import com.guorong.discount.config.DiscountStrategyConfigParser;
import com.guorong.exception.ConfigParserException;
import com.guorong.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 折扣策略注册表
 */
public class DiscountStrategyRegistry {

    private static final Logger LOGGER = Logger.getLogger(DiscountStrategyRegistry.class.getName());

    /**
     * 折扣策略集合
     */
    private final List<IDiscountStrategy> discountStrategies;

    public DiscountStrategyRegistry(String discountConfigPath) {
        DiscountStrategyConfigParser parser = new DiscountStrategyConfigParser(discountConfigPath);
        List<DiscountStrategyConfig> strategyConfigs = parser.loadConfig();
        this.discountStrategies =  parseConfigs(strategyConfigs);
    }

    /**
     * 选择可用策略，并且是级别最高的
     *
     * @param paymentContext 支付信息上下文
     * @return 折扣策略
     */
    public IDiscountStrategy selectStrategy(PaymentContext paymentContext) {
        ValidateUtils.validateArgument(Objects.isNull(paymentContext), "参数paymentContext不能为空");
        IDiscountStrategy selected = null;
        int maxPriority = Integer.MIN_VALUE;
        for (IDiscountStrategy strategy : discountStrategies) {
            if (strategy.isAvailable(paymentContext) && strategy.getPriority() >= maxPriority) {
                selected = strategy;
                maxPriority = strategy.getPriority();
            }
        }
        if (Objects.isNull(selected)) {
            throw new IllegalArgumentException(String.format("未找到可用策略: %s", paymentContext));
        }
        IDiscountStrategy finalSelected = selected;
        LOGGER.info(() -> String.format("选择了折扣策略：userId=%s, strategy=%s", paymentContext.getUserId(), finalSelected.getClass().getName()));
        return selected;
    }


    /**
     * 解析配置类
     *
     * @param strategyConfigs 配置类集合
     * @return 折扣策略集合
     */
    private List<IDiscountStrategy> parseConfigs(List<DiscountStrategyConfig> strategyConfigs) {
        List<IDiscountStrategy> strategies = new ArrayList<>();
        // 判断是否空
        if (Objects.isNull(strategyConfigs) || strategyConfigs.isEmpty()) {
            return strategies;
        }
        for (DiscountStrategyConfig config : strategyConfigs) {
            IDiscountStrategy discountStrategy = parseDiscountStrategy(config);
            strategies.add(discountStrategy);
            LOGGER.info(() -> String.format("注册折扣策略：%s", discountStrategy.getClass().getSimpleName()));
        }
        // 优先级数值降序排列
        strategies.sort(Comparator
                .comparingInt(IDiscountStrategy::getPriority)
                .reversed());
        return strategies;
    }

    /**
     * 将配置类解析为实际折扣
     *
     * @param strategyConfig 折扣配置类
     * @return 折扣优惠策略
     */
    private IDiscountStrategy parseDiscountStrategy(DiscountStrategyConfig strategyConfig) {
        ValidateUtils.validateArgument(Objects.isNull(strategyConfig), "参数config不能null");
        switch (strategyConfig.getType()) {
            case "DefaultDiscount":
                return new DefaultDiscountStrategy(
                        strategyConfig.getPriority(),
                        strategyConfig.getDiscountRate(),
                        strategyConfig.getPaymentType());

            case "MemberDiscount":
                return new MemberDiscountStrategy(
                        strategyConfig.getPriority(),
                        strategyConfig.getDiscountRate(),
                        strategyConfig.getRequiredMemberLevel());
            case "HolidayDiscount":
                return new HolidayDiscountStrategy(
                        strategyConfig.getPriority(),
                        strategyConfig.getDiscountRate(),
                        strategyConfig.getBeginHourOfDay(),
                        strategyConfig.getEndHourOfDay());
            default:
                throw new ConfigParserException(String.format("折扣策略类型未匹配: type=%s", strategyConfig.getType()));
        }
    }
}
