package com.guorong.factory.method.plan_2.strategy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * 折扣策略注册表
 */
public class DiscountStrategyRegistry {
    private static final Logger LOGGER = Logger.getLogger(DiscountStrategyRegistry.class.getName());

    /**
     * json解析器
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 折扣策略集合
     */
    private final List<IDiscountStrategy> strategies;

    public DiscountStrategyRegistry(String configPath) {
        List<DiscountStrategyConfig> configList = loadConfig(configPath);
        List<IDiscountStrategy> strategyList = new ArrayList<>();
        for (DiscountStrategyConfig config : configList) {
            IDiscountStrategy strategy = parseConfig(config);
            strategyList.add(strategy);
        }
        // 注册兜底策略(无折扣)
        strategyList.add(new NoDiscountStrategy());
        // 根据优先级进行排序
        strategyList.sort(Comparator.comparingInt(IDiscountStrategy::getPriority));
        strategies = strategyList;
    }

    /**
     * 获取折扣策略集合
     *
     * @return 折扣策略集合
     */
    public List<IDiscountStrategy> getStrategies() {
        return Collections.unmodifiableList(strategies);
    }

    /**
     * 解析折扣策略
     *
     * @param config 配置类
     * @return 折扣策略
     */
    private IDiscountStrategy parseConfig(DiscountStrategyConfig config) {
        switch (config.getType()) {
            case "OfflineDiscountStrategy":
                return new OfflineDiscountStrategy(
                        config.getDiscountRate(),
                        config.getPriority()
                );
            case "MemberDiscountStrategy":
                return new MemberDiscountStrategy(
                        config.getDiscountRate(),
                        config.getMemberLevel(),
                        config.getPriority()
                );
            default:
                throw new IllegalArgumentException(String.format("未知策略: type=%s", config.getType()));
        }
    }

    /**
     * 加载策略配置文件
     *
     * @param classPath 策略配置文件路径
     * @return 策略配置类
     */
    @SuppressWarnings("unchecked")
    private List<DiscountStrategyConfig> loadConfig(String classPath) {
        if (classPath == null || classPath.trim().isEmpty()) {
            throw new IllegalArgumentException("配置路径参数 configPath 不能为空");
        }
        try {
            // 读取配置文件
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(classPath);
            // 将json文件内容反序列化为java对象
            List<DiscountStrategyConfig> strategyConfigs = objectMapper.readValue(inputStream, new TypeReference<List<DiscountStrategyConfig>>() {});
            LOGGER.info(() -> String.format("折扣策略内容：%s", strategyConfigs.toString()));
            return strategyConfigs;
        } catch (IOException e) {
            String message = String.format("读取配置文件失败：configPath=%s", classPath);
            throw new RuntimeException(message, e);
        }
    }

}
