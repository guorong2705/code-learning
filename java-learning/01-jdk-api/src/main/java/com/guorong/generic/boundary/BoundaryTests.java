package com.guorong.generic.boundary;

import org.junit.jupiter.api.Test;

/**
 * 泛型边界
 */
public class BoundaryTests {

    @Test
    public void testBoundary() {
        UpItemHolder<Integer> integerUpHoldItem = new UpItemHolder<>(Integer.valueOf(11));
        System.out.println(integerUpHoldItem.getItem());

        UpItemHolder<Double> doubleUpHoldItem = new UpItemHolder<>(Double.valueOf(12.12));
        System.out.println(doubleUpHoldItem.getItem());
    }

    @Test
    public void test01() {
        Class<? extends Number> clazz = Number.class;
        clazz = Integer.class;
        clazz = Double.class;
    }

    @Test
    public void test02() {
        Class<Number> clazz = Number.class;
        // clazz = Double.class; 报错
        // clazz = Integer.class; 报错
    }


}

class ItemHolder<T> {
    private T item;
    public ItemHolder(T item) {
        this.item = item;
    }
    public T getItem() {
        return item;
    }
}

// 指定泛型上限
class UpItemHolder<T extends Number> extends ItemHolder<T> {
    public UpItemHolder(T item) {
        super(item);
    }
}
