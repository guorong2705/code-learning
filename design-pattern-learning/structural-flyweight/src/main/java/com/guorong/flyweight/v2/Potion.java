package com.guorong.flyweight.v2;

/**
 * 药水抽象类
 */
abstract class Potion {
    public abstract void drink();
}

/**
 * 康复药水
 */
class HealingPotion extends Potion {
    @Override
    public void drink() {
        String format = String.format("我康复了...  Potion={%s}  %s",
                this.getClass().getSimpleName(), System.identityHashCode(this));
        System.out.println(format);

    }
}

/**
 * 圣水药水
 */
class HolyWaterPotion extends Potion {
    @Override
    public void drink() {
        String format = String.format("我感觉很幸福...  Potion={%s}  %s",
                this.getClass().getSimpleName(), System.identityHashCode(this));
        System.out.println(format);
    }
}

/**
 * 隐形药水
 */
class InvisibilityPotion extends Potion {
    @Override
    public void drink() {
        String.format("我隐形了...  Potion={%s}  %s",
                this.getClass().getSimpleName(), System.identityHashCode(this));
    }
}

