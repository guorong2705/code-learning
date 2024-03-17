package com.guorong.validation.enum_validate.enums;

public enum SizeEnum implements EnumValidInterface {
    BIG("big","大码"),
    MIDDLE("middle","中码"),
    SMALL("small","小码"),
    ;

    SizeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
