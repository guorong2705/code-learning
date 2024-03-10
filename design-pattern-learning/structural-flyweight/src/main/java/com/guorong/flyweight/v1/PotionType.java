package com.guorong.flyweight.v1;

import java.io.Serializable;

/**
 * 药水类型枚举
 */
enum PotionType implements Serializable {
    /**康复*/
    HEALING,
    /**圣水*/
    HOLY_WATER,
    /**隐形*/
    INVISIBILITY,
    ;
}
