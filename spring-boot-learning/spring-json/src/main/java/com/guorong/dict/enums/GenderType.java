package com.guorong.dict.enums;


import com.guorong.dict.enums.Dict;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderType implements Dict {
    man("1", "男"),
    woman("0", "女"),
    ;

    private final String code;
    private final String name;
    
}
