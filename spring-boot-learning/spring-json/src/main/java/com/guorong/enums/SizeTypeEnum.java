package com.guorong.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum SizeTypeEnum implements Serializable {
    SMALL("small", "小"),
    MIDDLE("middle", "中"),
    BIG("big", "大"),
    ;
    private final String code;
    private final String name;

}
