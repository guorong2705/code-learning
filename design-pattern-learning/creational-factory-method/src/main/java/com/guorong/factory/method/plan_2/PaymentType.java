package com.guorong.factory.method.plan_2;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付类型
 */
@Getter
@AllArgsConstructor
public enum PaymentType {
    ALIPAY("支付宝"),
    WECHAT("微信支付"),
    ;
    private final String displayName;
}
