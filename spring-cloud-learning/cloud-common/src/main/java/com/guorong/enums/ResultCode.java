package com.guorong.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum ResultCode implements Serializable {

    RC_200("200", "成功"),
    RC_500("500", "服务器错误"),
    RC_404("404", "未发现"),
    ;
    private final String code;
    private final String name;
}
