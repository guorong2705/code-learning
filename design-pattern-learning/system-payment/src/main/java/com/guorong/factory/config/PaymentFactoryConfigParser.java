package com.guorong.factory.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guorong.exception.ConfigParserException;
import com.guorong.utils.StringUtils;
import com.guorong.utils.ValidateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 支付工厂配置解析器
 */
public class PaymentFactoryConfigParser {

    private static final Logger LOGGER = Logger.getLogger(PaymentFactoryConfigParser.class.getName());

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 支付工厂配置文件路径
     */
    private final String configPath;
    /**
     * 类路径前缀
     */
    private static final String CLASS_PATH_PREFIX = "classpath:";

    public PaymentFactoryConfigParser(String configPath) {
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(configPath), "参数configPath不能为空");
        this.configPath = configPath;
    }

    /**
     * 加载工厂配置属性
     *
     * @return 支付工厂配置类集合
     */
    public List<PaymentFactoryConfig> loadConfig() {
        String path = configPath;
        if (path.startsWith(CLASS_PATH_PREFIX)) {
            path = path.replace(CLASS_PATH_PREFIX, "");
        }
        // 读取配置文件
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
        if (Objects.isNull(inputStream)) {
            throw new ConfigParserException(String.format("支付工厂配置文件不存在: configPath=%s", path));
        }
        try {
            List<PaymentFactoryConfig> configs = objectMapper.readValue(inputStream, new TypeReference<List<PaymentFactoryConfig>>() {
            });
            // 验证配置
            configs.forEach(PaymentFactoryConfig::validateConfig);

            String configJson = objectMapper.writeValueAsString(configs);
            LOGGER.info(() -> String.format("读取工厂配置信息：%s", configJson));
            return configs;
        } catch (IOException e) {
            throw new ConfigParserException(String.format("支付工厂配置解析错误：configPath=%s", path));
        }
    }
}
