package com.guorong.factory.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

class CoinFactory {

    @AllArgsConstructor
    enum CoinType {
        GOLD(GoldCoin::new),
        COPPER(CopperCoin::new),
        ;
        @Getter
        private Supplier<Coin> supplier;
    }

    /**
     * 获取硬币: 相同类型硬币使用单例模式，每种类型只存在一个实例
     * @param coinType 硬币类型
     * @return
     */
    public static Coin getCoin(CoinType coinType) {
        return coinType.getSupplier().get();
    }
}
