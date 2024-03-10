package com.guorong.factory.v3;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Constructor;

class CoinFactory {
    @Getter
    @AllArgsConstructor
    enum CoinType {
        GOLD(GoldCoin.class),
        COPPER(CopperCoin.class),
        ;
        private final Class<? extends Coin> clazz;
    }

    /**
     * 每次都创建一个新的 Coin 实例
     * @param coinType
     * @return
     */
    public static Coin createCoin(CoinType coinType) {
        try {
            Class<? extends Coin> clazz = coinType.getClazz();
            Constructor<? extends Coin> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
