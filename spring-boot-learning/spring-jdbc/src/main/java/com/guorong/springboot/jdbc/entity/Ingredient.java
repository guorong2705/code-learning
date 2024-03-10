package com.guorong.springboot.jdbc.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 玉米饼 成分实
 *
 * @author guorong
 * @date 2020-05-14
 */
@Data
@RequiredArgsConstructor
public class Ingredient {

    /**
     * 主键
     */
    private final String id;

    /**
     * 玉米饼成分名称
     */
    private final String name;

    /**
     * 玉米饼成分类型
     */
    private final Type type;

    /**
     * 类型枚举
     */
    public static enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE;
    }

}
