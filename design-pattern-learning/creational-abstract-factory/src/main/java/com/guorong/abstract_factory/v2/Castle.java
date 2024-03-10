package com.guorong.abstract_factory.v2;

/**
 * 城堡
 */
interface Castle {
    String getDescription();
}

/**
 * 精灵城堡
 */
class ElfCastle implements Castle {
    private static final String DESCRIPTION = "这是一个精灵城堡";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

/**
 * 兽人城堡
 */
class OrcCastle implements Castle {
    private static final String DESCRIPTION = "这是一个兽人城堡";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}