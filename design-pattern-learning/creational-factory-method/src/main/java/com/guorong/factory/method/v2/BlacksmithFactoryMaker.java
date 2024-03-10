package com.guorong.factory.method.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

/**
 *  工厂制造器
 */
class BlacksmithFactoryMaker {
    @Getter
    @AllArgsConstructor
    enum FactoryType {
        // 精灵
        ELF_BLACKSMITH(ElfBlacksmithFactory::new),
        // 兽人
        ORC_BLACKSMITH(OrcBlacksmithFactory::new),
        ;
        private final Supplier<BlacksmithFactory> supplier;
    }

    /**
     * 获取工厂实例
     * @param factoryType
     * @return
     */
    public static BlacksmithFactory getFactory(FactoryType factoryType) {
        return factoryType.supplier.get();
    }
}
