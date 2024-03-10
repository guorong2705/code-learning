package com.guorong.flyweight.v2;

import java.util.Random;

/**
 * 炼金术士的商店里的货架上摆满了魔法药剂。许多药水是相同的，
 * 因此无需为每个药水创建一个新对象。相反，一个对象实例可以代表多个架子项，因此内存占用量仍然很小。
 */
class App {
    private static Random random = new Random(75);
    public static void main(String[] args) {
        PotionFactory factory = new PotionFactory();
        PotionType[] potionTypes = PotionType.values();
        for (int i = 0; i < 7; i++) {
            PotionType potionType = potionTypes[random.nextInt(PotionType.values().length)];
            factory.getPotion(potionType).drink();
        }
    }
}
