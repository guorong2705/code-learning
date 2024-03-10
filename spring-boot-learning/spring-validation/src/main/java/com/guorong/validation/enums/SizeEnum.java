package com.guorong.validation.enums;

import com.guorong.validation.annotation.EnumValidInterface;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.stream.Collectors;

public enum SizeEnum implements Serializable, EnumValidInterface {
    BIG("1","大码"),
    MIDDLE("2","中码"),
    SMALL("3","小码"),
    ;

    SizeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
