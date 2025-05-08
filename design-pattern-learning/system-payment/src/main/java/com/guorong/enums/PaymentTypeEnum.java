package com.guorong.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * 支付枚举
 */
@Getter
public enum PaymentTypeEnum implements Serializable {
    ALIPAY("支付宝"),
    WECHAT("微信支付"),
    ;
    /**
     * 展示名称
     */
    private final String displayName;

    PaymentTypeEnum(String displayName) {
        this.displayName = displayName;
    }
}
