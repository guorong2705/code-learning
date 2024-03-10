package com.guorong.builder.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 英雄
 */
@Getter
@Setter
@ToString
class Hero {
    // 职业
    private final String profession;
    // 姓名
    private final String name;
    // 头发类型
    private  String hairType;
    // 头发颜色
    private  String hairColor;
    // 盔甲
    private String armor;
    // 武器
    private String weapon;
    public Hero(String profession, String name) {
        this.profession = profession;
        this.name = name;
    }


    static class Builder {
        private Hero hero;

        public Builder(String profession, String name) {
            hero = new Hero(profession, name);
        }

        public Builder hairType(String hairType) {
            hero.setHairType(hairType);
            return this;
        }

        public Builder hairColor(String hairColor) {
            hero.setHairColor(hairColor);
            return this;
        }

        public Builder armor(String armor) {
            hero.setArmor(armor);
            return this;
        }

        public Builder weapon(String weapon) {
            hero.setWeapon(weapon);
            return this;
        }

        public Hero build() {
            return hero;
        }
    }
}
