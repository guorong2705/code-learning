package com.guorong.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 人种
 */
@AllArgsConstructor
@Getter
public enum PersonTypeEnum implements Serializable {
    BLACK("black", "黑种人"),
    WHITE("white", "白种人"),
    YELLOW("yellow", "黄种人"),
    ;
    private final String code;
    @JsonValue
    private final String name;
}
