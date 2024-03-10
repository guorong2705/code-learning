package com.guorong.generic.generic_interface_1;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Supplier 函数是接口，提供一个创建器方法
 * Iterable 需要实现一个迭代器方法
 */
class CoffeeSupplier implements Supplier<Coffee>, Iterable<Coffee> {

    private Random random = new Random(47);

    private int size = 0;

    /**
     * 类型库数组
     */
    private CoffeeTypeEnum[] types;

    {
        types = CoffeeTypeEnum.values();
    }

    public CoffeeSupplier(int size) {
        this.size = size;
    }

    /**
     * 实现 Supplier 接口中的方法
     *
     * @return
     */
    @Override
    public Coffee get() {
        try {
            CoffeeTypeEnum type = types[random.nextInt(types.length)];
            return (Coffee) type.getClazz().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    // 迭代器实现
    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }
        @Override
        public Coffee next() {
            count--;
            // 调用当前内部类的外部类生成器的方法
            return CoffeeSupplier.this.get();
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * 开发获取迭代器方法
     * @return
     */
    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }


}
