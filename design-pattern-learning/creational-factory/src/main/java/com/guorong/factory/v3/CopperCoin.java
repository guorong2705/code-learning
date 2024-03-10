package com.guorong.factory.v3;

public class CopperCoin implements Coin {
    private static final String DESCRIPTION = "这是一个铜币";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
