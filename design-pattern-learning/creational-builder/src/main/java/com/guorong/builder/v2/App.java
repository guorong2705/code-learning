package com.guorong.builder.v2;

class App {
    public static void main(String[] args) {
        Hero hero = new Hero.Builder("刺客", "荆轲")
                .hairType("长发")
                .hairColor("黑色")
                .armor("盔甲")
                .weapon("五米大刀")
                .build();
        System.out.println(hero);
    }
}
