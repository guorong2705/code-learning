package com.guorong.abstract_factory.v1;

/**
 * 王国工厂
 */
interface KingdomFactory {
    /**
     * 创建城堡
     * @return
     */
    Castle createCastle();
    /**
     * 创建国王
     * @return
     */
    King createKing();
    /**
     * 创建军队
     * @return
     */
    Army createArmy();
}


/**
 * 精灵王国工厂
 */
class ElfKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }
    @Override
    public King createKing() {
        return new ElfKing();
    }
    @Override
    public Army createArmy() {
        return new ElfArmy();
    }
}


/**
 * 兽人王国工厂
 */
class OrcKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new OrcCastle();
    }
    @Override
    public King createKing() {
        return new OrcKing();
    }
    @Override
    public Army createArmy() {
        return new OrcArmy();
    }
}

