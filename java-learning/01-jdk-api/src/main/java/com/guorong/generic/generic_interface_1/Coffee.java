package com.guorong.generic.generic_interface_1;

import lombok.Getter;

class Coffee {

    private static long counter = 1;

    /**
     * 创建对象时，递增 id
     */
    private final long id = counter++;

    @Override
    public String toString() {
        return String.format("%s = %s", getClass().getSimpleName(), id);
    }
}
enum CoffeeTypeEnum {
    LATTE(com.guorong.generic.generic_interface_1.Latte.class),
    MOCHA(com.guorong.generic.generic_interface_1.Mocha.class),
    CAPPUCCINO(com.guorong.generic.generic_interface_1.Cappuccino.class),
    ;
    @Getter
    private Class<?> clazz;

    CoffeeTypeEnum(Class<?> clazz) {
        this.clazz = clazz;
    }
}

class Latte extends Coffee {
}

class Mocha extends Coffee {
}

class Cappuccino extends Coffee {
}