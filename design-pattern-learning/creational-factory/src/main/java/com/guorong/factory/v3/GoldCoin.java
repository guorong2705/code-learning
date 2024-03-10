package com.guorong.factory.v3;

public class GoldCoin implements Coin {
    private static final String DESCRIPTION = "这是一个金币";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
