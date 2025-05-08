package com.guorong.discount.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guorong.discount.DefaultDiscountStrategy;
import com.guorong.discount.HolidayDiscountStrategy;
import com.guorong.discount.IDiscountStrategy;
import com.guorong.discount.MemberDiscountStrategy;
import com.guorong.exception.ConfigParserException;
import com.guorong.utils.StringUtils;
import com.guorong.utils.ValidateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 折扣策略配置读取器
 */
public class DiscountStrategyConfigParser {
    private static final Logger LOGGER = Logger.getLogger(DiscountStrategyConfigParser.class.getName());

    /**
     * JSON解析器
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 配置文件路径
     */
    private final String configPath;
    /**
     * 类路径前缀
     */
    private static final String CLASS_PATH_PREFIX = "classpath:";

    public DiscountStrategyConfigParser(String configPath) {
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(configPath), "configPath不能为空");
        this.configPath = configPath;
    }

    /**
     * 加载折扣策略文件
     *
     * @return 折扣优惠配置集合
     * @throws IllegalArgumentException configClassPath为空或者文件不存在
     * @throws ConfigParserException    折扣策略配置文件解析错误
     */
    public List<DiscountStrategyConfig> loadConfig() {
        String path = configPath;
        if (path.startsWith(CLASS_PATH_PREFIX)) {
            path = path.replace(path, "");
        }
        // 读取配置
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
        if (Objects.isNull(inputStream)) {
            throw new IllegalArgumentException(String.format("配置文件未知: configPath=%s", configPath));
        }
        // 解析配置
        try {
            List<DiscountStrategyConfig> configList = objectMapper.readValue(inputStream, new TypeReference<List<DiscountStrategyConfig>>() {
            });
            // 校验参数
            configList.forEach(DiscountStrategyConfig::validate);
            String jsonStr = objectMapper.writeValueAsString(configList);
            LOGGER.info(() -> String.format("折扣策略配置：%s", jsonStr));
            return configList;
        } catch (IOException e) {
            throw new ConfigParserException(String.format("配置文件解析错误：configClassPath=%s", configPath), e);
        }
    }

}
