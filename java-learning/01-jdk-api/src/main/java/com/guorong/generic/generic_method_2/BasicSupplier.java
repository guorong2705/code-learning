package com.guorong.generic.generic_method_2;

import java.util.function.Supplier;

/**
 *
 */
class BasicSupplier<T> implements Supplier<T> {

    private final Class<T> typeClass;

    public BasicSupplier(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public T get() {
        try {
            return typeClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过静态方法创建 Supplier 对象
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Supplier<T> create(Class<T> type) {
        return new BasicSupplier<>(type);
    }

}
