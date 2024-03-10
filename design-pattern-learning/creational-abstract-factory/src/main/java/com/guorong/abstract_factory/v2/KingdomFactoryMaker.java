package com.guorong.abstract_factory.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 工厂制作器: 工厂使用单例实现
 */
class KingdomFactoryMaker {
    @Getter
    @AllArgsConstructor
    enum KingdomType {
        // 精灵王国工厂
        ELF(ElfKingdomFactory::new),
        // 兽人王国工厂
        ORC(OrcKingdomFactory::new),
        ;
        private final Supplier<KingdomFactory> supplier;
    }

    // 单例工厂储存仓库
    private static Map<KingdomType, KingdomFactory> kingdomFactoryMap = new HashMap<>();

    static {
        // 初始化单例工厂
        Arrays.stream(KingdomType.values())
                .forEach(kingdomType -> kingdomFactoryMap.put(kingdomType, kingdomType.supplier.get()));
    }

    /**
     * 获取工厂
     * @param kingdomType
     * @return
     */
    public static KingdomFactory makeFactory(KingdomType kingdomType) {
        return kingdomFactoryMap.get(kingdomType);
    }

}
