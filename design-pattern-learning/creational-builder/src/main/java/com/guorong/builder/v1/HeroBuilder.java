package com.guorong.builder.v1;

class HeroBuilder {
    private final String profession;
    private final String name;
    private  String hairType;
    private  String hairColor;
    // 盔甲
    private String armor;
    private String weapon;

    public HeroBuilder(String profession, String name) {
        this.profession = profession;
        this.name = name;
    }

    public HeroBuilder hairType(String hairType) {
        this.hairType = hairType;
        return this;
    }

    public HeroBuilder hairColor(String hairColor) {
        this.hairColor = hairColor;
        return this;
    }

    public HeroBuilder armor(String armor) {
        this.armor = armor;
        return this;
    }

    public HeroBuilder weapon(String weapon) {
        this.weapon = weapon;
        return this;
    }

    public Hero build() {
        Hero hero = new Hero(this.profession, this.name);
        hero.setHairType(this.hairType);
        hero.setHairColor(this.hairColor);
        hero.setArmor(this.armor);
        hero.setWeapon(this.weapon);
        return hero;
    }
}
