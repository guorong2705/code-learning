package com.guorong.mp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 删除枚举
 */
@Getter
public enum DeleteStatusEnum {
    NOT_DELETE(0, "未删除"),
    DELETED(1, "已删除"),
    ;
    @EnumValue // 枚举映射数据库字段
    @JsonValue // json序列化显示的值
    private final Integer value;
    private final String desc;

    DeleteStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
