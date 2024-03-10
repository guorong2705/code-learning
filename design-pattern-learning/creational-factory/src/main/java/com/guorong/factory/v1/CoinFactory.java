package com.guorong.factory.v1;

class CoinFactory {
    enum CoinType {
        GOLD,
        COPPER,
        ;
    }

    public static Coin getCoin(CoinType coinType) {
        switch (coinType) {
            case GOLD:
                return new GoldCoin();
            case COPPER:
                return new CopperCoin();
            default:
                throw new IllegalArgumentException("不存在该类型的硬币");
        }
    }
}
