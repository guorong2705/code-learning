package com.guorong.facade.v1;

/**
 * 持久层代码生成器
 * @author guorong
 */
public class PersistentGenerator {

    public void generate() {
        // 从配置管理中获取配置数据
        ConfigModel configModel = ConfigManager.getInstance().getConfigModel();
        if (configModel.isNeedGeneratePersistent()) {
            // 生成持久层代码，并保存成文件
            System.out.println("正在生成持久层代码....");
        }
    }
}
