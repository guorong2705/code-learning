package com.guorong._enum;

import java.util.Random;

public class EnumMain {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (Course value : Course.values()) {
                Food food = value.randSelect();
                System.out.println(food);
            }
            System.out.println("********************");
        }
    }
}

interface Food {
    enum Drink implements Food {
        COCA_COLA, PEPSI_COLA, COFFEE,
        ;
    }

    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        ;
    }

    enum Dessert implements Food {
        TIRAMISU, GELATO, FRUIT;
    }
}

enum Course {
    DRINK(Food.Drink.class),
    MAIN_COURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    ;

    private Food[] values;

    Course(Class<? extends Food> kind) {
        this.values = kind.getEnumConstants();
    }

    public Food randSelect() {
        return Enums.random(values);
    }
}

class Enums {

    private static Random random = new Random(45);

    public static <T extends Enum<T>> T random(Class<T> clazz) {
        return random(clazz.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[random.nextInt(values.length)];
    }
}

