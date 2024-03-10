package com.guorong.abstract_factory.v2;

/**
 * 国王
 */
interface King {
    String getDescription();
}

/**
 * 精灵国王
 */
class ElfKing implements King {
    private static final String DESCRIPTION = "这是一个精灵国王";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

/**
 * 兽人国王
 */
class OrcKing implements King {
    private static final String DESCRIPTION = "这是一个兽人国王";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}