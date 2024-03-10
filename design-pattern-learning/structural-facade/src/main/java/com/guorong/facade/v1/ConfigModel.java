package com.guorong.facade.v1;

import lombok.Getter;
import lombok.Setter;

/**
 * 配置模型
 */
@Getter
@Setter
class ConfigModel {

    /**
     * 是否生成控制层：默认生成
     */
    private boolean needGenerateController  = true;

    /**
     * 是否生成逻辑层：默认生成
     */
    private boolean needGenerateService = true;

    /**
     * 是否生成持久层：默认生成
     */
    private boolean needGeneratePersistent = true;

}
