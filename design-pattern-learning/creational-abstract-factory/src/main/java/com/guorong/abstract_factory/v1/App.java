package com.guorong.abstract_factory.v1;

import lombok.SneakyThrows;


/**
 * 抽象工厂模式
 */
class App {
    @SneakyThrows
    public static void main(String[] args) {
        KingdomFactory elfKingdomFactory = KingdomFactoryMaker.makeFactory(KingdomFactoryMaker.KingdomType.ELF);
        showKingdom(elfKingdomFactory);
        System.out.println("==========================================");
        KingdomFactory orcKingdomFactory = KingdomFactoryMaker.makeFactory(KingdomFactoryMaker.KingdomType.ORC);
        showKingdom(orcKingdomFactory);
    }

    /**
     * 显示王国成员
     * @param kingdomFactory
     */
    private static void showKingdom(KingdomFactory kingdomFactory) {
        // 国王
        System.out.println(kingdomFactory.createKing().getDescription());
        // 城堡
        System.out.println(kingdomFactory.createCastle().getDescription());
        // 军队
        System.out.println(kingdomFactory.createArmy().getDescription());
    }
}
