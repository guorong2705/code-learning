package com.guorong.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum SeasonEnum implements Serializable {
    SPRING("spring", "春天"),
    SUMMER("summer", "夏天"),
    AUTUMN("autumn", "秋天"),
    WINTER("winter", "冬天"),
    ;
    private final String code;

    private final String name;
}
