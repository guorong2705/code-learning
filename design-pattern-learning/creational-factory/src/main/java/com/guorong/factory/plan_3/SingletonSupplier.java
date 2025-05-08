package com.guorong.factory.plan_3;

import java.util.function.Supplier;

/**
 * 单例 Supplier
 *
 * @param <T>
 */
class SingletonSupplier<T> implements Supplier<T> {

    private final T instance;

    public SingletonSupplier(T instance) {
        this.instance = instance;
    }

    @Override
    public synchronized T get() {
        return instance;
    }
}
