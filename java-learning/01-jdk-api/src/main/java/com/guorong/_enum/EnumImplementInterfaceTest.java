package com.guorong._enum;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.Supplier;

/**
 * 枚举实现接口：
 * 枚举默认继承 Enum 类，由于 java 不支持多继承，所有创建枚举时候不能再使用继承，但是枚举可以实现接口。
 */
public class EnumImplementInterfaceTest {

    /**
     * 枚举实现函数式接口
     */
    @Getter
    private enum ColorEnum implements Supplier<ColorEnum> {
        BLUE(2, "蓝色"),
        YELLOW(3, "黄色"),
        BLACK(4, "黑色"),
        RED(1, "红色"),
        ;
        private final Integer code;
        private final String name;

        private static Random random = new Random();

        ColorEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * 获取当前枚举中的 随机实例
         * @return
         */
        @Override
        public ColorEnum get() {
            ColorEnum[] colorEnums = ColorEnum.values();
            return colorEnums[random.nextInt(colorEnums.length)];
        }
    }


    /**
     * 枚举工具
     */
    private static class EnumUtil {

        private static Random random = new Random();

        /**
         * 获取指定枚举的 随机实例
         * @param <T> 枚举类型（T extends Enum<T>）限定 T 为枚举类型
         * @return
         */
        public static <T extends Enum<T>> T randomInstance(Class<T> enumClass) {
            T[] enums = enumClass.getEnumConstants();
            return enums[random.nextInt(enums.length)];
        }
    }


    /**
     * 获取随机枚举实例
     */
    @Test
    public void test01() {
        ColorEnum black = ColorEnum.BLACK;
        for (int i = 0; i < 5; i++) {
            System.out.println(black.get());
        }
    }

    /**
     * 获取随机枚举实例
     */
    @Test
    public void test02() {
        // EnumUtil.randomInstance(Integer.class); // 报错类型不匹配
        for (int i = 0; i < 5; i++) {
            ColorEnum colorEnum = EnumUtil.randomInstance(ColorEnum.class);
            System.out.println(colorEnum);
        }
    }








}
