package com.guorong.abstract_factory.v2;

/**
 * 抽象工具模式
 */
class App {
    public static void main(String[] args) {
        // 精灵工厂
        KingdomFactory elfKingdomFactory = KingdomFactoryMaker.makeFactory(KingdomFactoryMaker.KingdomType.ELF);
        showFactory(elfKingdomFactory);
        System.out.println("========================================");
        // 兽人工厂
        KingdomFactory orcKingdomFactory = KingdomFactoryMaker.makeFactory(KingdomFactoryMaker.KingdomType.ORC);
        showFactory(orcKingdomFactory);
    }

    private static void showFactory(KingdomFactory kingdomFactory) {
        // 国王
        System.out.println(kingdomFactory.createKing().getDescription());
        // 城堡
        System.out.println(kingdomFactory.createCastle().getDescription());
        // 军队
        System.out.println(kingdomFactory.createArmy().getDescription());
    }
}
