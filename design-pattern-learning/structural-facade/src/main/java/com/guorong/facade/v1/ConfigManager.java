package com.guorong.facade.v1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * 配置管理
 *
 * @author guorong
 */
class ConfigManager {

    /**
     * 配置管理实例（单例模式）
     */
    private static ConfigManager configManager = new ConfigManager();

    /**
     * 配置数据
     */
    private static ConfigModel configModel = new ConfigModel();

    static {
        try {
            InputStream inputStream = ConfigManager.class.getClassLoader()
                    .getResourceAsStream("configModel.properties");
            Properties prop = new Properties();
            prop.load(inputStream);

            // Controller
            String needGenerateController = prop.getProperty("needGenerateController");
            if (Objects.nonNull(needGenerateController) && !needGenerateController.isEmpty()) {
                configModel.setNeedGenerateController(Boolean.parseBoolean(needGenerateController));
            }
            // Service
            String needGenerateService = prop.getProperty("needGenerateService");
            if (Objects.nonNull(needGenerateService) && !needGenerateService.isEmpty()) {
                configModel.setNeedGenerateService(Boolean.parseBoolean(needGenerateService));
            }
            // Persistent
            String needGeneratePersistent = prop.getProperty("needGeneratePersistent");
            if (Objects.nonNull(needGeneratePersistent) && needGeneratePersistent.isEmpty()) {
                configModel.setNeedGeneratePersistent(Boolean.parseBoolean(needGeneratePersistent));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConfigManager() {
    }

    /**
     * 获取配置管理实例
     *
     * @return
     */
    public static ConfigManager getInstance() {
        return configManager;
    }

    /**
     * 获取配置数据
     *
     * @return
     */
    public ConfigModel getConfigModel() {
        return configModel;
    }


}
