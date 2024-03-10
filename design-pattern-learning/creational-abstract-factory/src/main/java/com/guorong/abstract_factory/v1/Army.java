package com.guorong.abstract_factory.v1;

/**
 * 军队
 */
interface Army {
    String getDescription();
}


/**
 * 精灵军队
 */
class ElfArmy implements Army {
    private static final String DESCRIPTION = "这是一个精灵军队";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}


/**
 * 兽人军队
 */
class OrcArmy implements Army {
    private static final String DESCRIPTION = "这是一个兽人军队";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

