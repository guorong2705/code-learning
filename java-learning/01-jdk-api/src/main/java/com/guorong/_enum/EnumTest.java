package com.guorong._enum;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

/**
 * 所有的枚举类型都是 Enum 类的子类。它们继承了这个类的许多方法。 其中最有用的一个是 toString，
 * 这个方法能够返回枚举常量名。例如，Size.SMALL.toString()将返回字符串“SMALL”。
 * @author guorong
 * @date 2021-07-31
 */
public class EnumTest {

    private enum ColorEnum {
        BLUE(2, "蓝色"),
        YELLOW(3, "黄色"),
        BLACK(4, "黑色"),
        RED(1, "红色"),
        ;
        @Getter
        private final Integer code;
        @Getter
        private final String name;

        ColorEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * 通过code获取枚举实例
         * @param code
         * @return
         */
        public static ColorEnum getByCode(Integer code) {
            for (ColorEnum value : ColorEnum.values()) {
                if (Objects.equals(value.getCode(), code)) {
                    return value;
                }
            }
            return null;
        }

        public static void main(String[] args) {

        }
    }

    /**
     * 获取枚举所有实例
     */
    @Test
    public void testValues() {
        ColorEnum[] colorEnums = ColorEnum.values();
        Arrays.stream(colorEnums).forEach(System.out::println);
    }

    /**
     * 获取枚举所有实例
     */
    @Test
    public void testEnumConstants() {
        ColorEnum[] enumConstants = ColorEnum.class.getEnumConstants();
        Arrays.stream(enumConstants).forEach(System.out::println);
    }


    /**
     * 通过枚举实例名称获取 指定实例
     */
    @Test
    public void testValueOf() {
        ColorEnum red = ColorEnum.valueOf("RED");
        ColorEnum blue = Enum.valueOf(ColorEnum.class, "BLUE");
    }

    /**
     * 获取枚举实例在 枚举中的序号，从0开始
     */
    @Test
    public void testOrdinal() {
        ColorEnum[] colorEnums = ColorEnum.values();
        Arrays.stream(colorEnums)
                .map(e -> e.name() + " = " + e.ordinal())
                .forEach(System.out::println);
    }



}
