package com.guorong._enum;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;

/**
 * EnumMap 是一种特殊的 Map，它要求其中的键（key）必须来自一个 enum，由于 enum 本身的限制，
 * 所以 EnumMap 在内部可由数组实现。因此 EnumMap 的速度很快，可以放心地使用 enum 实例在 EnumMap 中进行查找操作。
 * 不过，我们只能将 enum 的实例作为键来调用 put() 可方法，其他操作与使用一般的 Map 差不多。
 */
class EnumMapTest {

    public static void main(String[] args) {
        EnumMap<ColorEnum, Integer> enumMap = new EnumMap<ColorEnum, Integer>(ColorEnum.class);
        for (ColorEnum colorEnum : ColorEnum.values()) {
            enumMap.put(colorEnum, colorEnum.ordinal());
        }
        enumMap.entrySet().forEach(System.out::println);
    }

    private enum ColorEnum {
        RED,BLUE,BLACK,YELLOW,;
    }


}
