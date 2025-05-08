package com.guorong.factory.config;

import com.guorong.utils.StringUtils;
import com.guorong.utils.ValidateUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class PaymentFactoryConfig implements Serializable {
    /**
     * 支付类型
     */
    private String paymentType;

    /**
     * 最大支付金额
     */
    private  Double maxAmount;

    /**
     * 是否开启短信通知
     */
    private  Boolean smsEnabled;

    /**
     * 是否开启邮件通知
     */
    private  Boolean emailEnabled;

    /**
     * 验证配置
     */
    public void validateConfig() {
        ValidateUtils.validateArgument(StringUtils.isStrEmpty(paymentType), "属性paymentType不能为空");
        ValidateUtils.validateArgument(Objects.isNull(maxAmount), "属性maxAmount不能为null");
        ValidateUtils.validateArgument(Objects.isNull(smsEnabled), "属性smsEnabled不能为null");
        ValidateUtils.validateArgument(Objects.isNull(emailEnabled), "属性emailEnable不能为null");
    }
}
