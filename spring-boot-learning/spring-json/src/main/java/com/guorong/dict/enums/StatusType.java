package com.guorong.dict.enums;

import com.guorong.dict.enums.Dict;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusType implements Dict {
    enable("1", "启用"),
    disable("0", "禁用"),
    ;

    private final String code;
    private final String name;

}
