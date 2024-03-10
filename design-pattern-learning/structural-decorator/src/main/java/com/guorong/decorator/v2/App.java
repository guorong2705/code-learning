package com.guorong.decorator.v2;

class App {
    public static void main(String[] args) {
        Troll troll = new SimpleTroll();
        // 让巨魔拥有 锤子
        troll = new HammerDecorator(troll);
        troll.attack();

        System.out.println("===========================");

        // 让巨魔拥有 棍子
        troll = new ClubbedDecorator(troll);
        troll.attack();
    }
}
