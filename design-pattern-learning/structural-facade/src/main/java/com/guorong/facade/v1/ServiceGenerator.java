package com.guorong.facade.v1;

/**
 * 逻辑层代码生成器
 */
class ServiceGenerator {

    public void generate() {
        // 从配置管理中获取配置数据
        ConfigModel configModel = ConfigManager.getInstance().getConfigModel();
        if (configModel.isNeedGenerateService()) {
            // 生成逻辑层代码，并保存文件
            System.out.println("正在生成逻辑层代码....");
        }
    }

}
