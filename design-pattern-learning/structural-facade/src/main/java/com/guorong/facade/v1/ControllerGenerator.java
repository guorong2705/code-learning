package com.guorong.facade.v1;

/**
 * 控制层代码生成器
 */
class ControllerGenerator {

    /**
     * 生成控制层代码
     */
    public void generate() {
        // 从配置管理中获取配置信息
        ConfigModel configModel = ConfigManager.getInstance().getConfigModel();
        if (configModel.isNeedGenerateController()) {
            // 按照要求去生成相应的代码，并保存成文件
            System.out.println("正在生成控制层代码....");
        }
    }

}
