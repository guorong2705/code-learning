package com.guorong.builder.v1;

public class App {
    public static void main(String[] args) {
        Hero hero = new HeroBuilder("刺客", "荆轲")
                .hairType("长发")
                .hairColor("黑色")
                .armor("燕菱甲")
                .weapon("匕首")
                .build();
        System.out.println(hero);
    }
}
