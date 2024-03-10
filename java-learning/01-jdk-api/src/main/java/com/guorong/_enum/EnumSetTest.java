package com.guorong._enum;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;

/**
 * EnumSet 的设计充分考虑到了速度因素，因为它必须与非常高效的 bit 标志相竞争（其操作与 HashSet 相比，非常地快），
 * 就其内部而言，它（可能）就是将一个 long 值作为比特向量，所以 EnumSet 非常快速高效。使用 EnumSet 的优点是，
 * 它在说明一个二进制位是否存在时，具有更好的表达能力，并且无需担心性能。EnumSet 中的元素必须来自一个 enum。
 */
public class EnumSetTest {

    private enum ColorEnum {
        RED,BLUE,BLACK,YELLOW,GREEN;
    }

    /**
     * 创建一个空集合
     */
    @Test
    public void testOneOf() {
        // 创建一个空集合
        EnumSet<ColorEnum> enumSet = EnumSet.noneOf(ColorEnum.class);
        System.out.println(enumSet); // []
        enumSet.add(ColorEnum.RED);
        System.out.println(enumSet); // [RED]
    }

    @Test
    public void testOf() {
        // 创建一个包含元素的集合
        EnumSet<ColorEnum> enumSet = EnumSet.of(ColorEnum.RED, ColorEnum.BLUE);
        System.out.println(enumSet); // [RED, BLUE]
    }

    @Test
    public void testAllOf() {
        // 获取枚举中的全部实例
        EnumSet<ColorEnum> enumSet = EnumSet.allOf(ColorEnum.class);
        System.out.println(enumSet); // [RED, BLUE, BLACK]
    }

    @Test
    public void testRange() {
        // 获取指定枚举实例之间枚举实例集合
        EnumSet<ColorEnum> enumSet = EnumSet.range(ColorEnum.RED, ColorEnum.YELLOW);
        System.out.println(enumSet); // [RED, BLUE, BLACK, YELLOW]
    }




















}
