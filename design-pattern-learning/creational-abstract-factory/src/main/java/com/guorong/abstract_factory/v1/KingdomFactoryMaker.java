package com.guorong.abstract_factory.v1;

/**
 * 工厂制作器
 */
class KingdomFactoryMaker {

    /**
     * 王国类型
     */
    enum KingdomType {
        ELF,
        ORC,
        ;
    }

    /**
     * 创建王国工厂
     *
     * @return
     */
    public static KingdomFactory makeFactory(KingdomType kingdomType) {
        switch (kingdomType) {
            case ELF:
                // 精灵王国工厂
                return new ElfKingdomFactory();
            case ORC:
                // 兽人王国工厂
                return new OrcKingdomFactory();
            default:
                throw new IllegalArgumentException("KingdomType type notSupport");
        }
    }

}
