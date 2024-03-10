package com.guorong.flyweight.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 炼金术士的商店里的货架上摆满了魔法药剂。许多药水是相同的，
 * 因此无需为每个药水创建一个新对象。相反，一个对象实例可以代表多个架子项，因此内存占用量仍然很小。
 */
class App {
    public static void main(String[] args) {
        PotionFactory factory = new PotionFactory();
        List<Potion> list = new ArrayList<>();
        Random random = new Random();
        PotionType[] potionTypes = PotionType.values();
        for (int i = 0; i < 20; i++) {
            int randNum = random.nextInt(PotionType.values().length);
            PotionType potionType = potionTypes[randNum];
            list.add(factory.getPotion(potionType));
        }
        list.forEach(Potion::drink);
    }
}
