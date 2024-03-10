package com.guorong.generic.generic_interface_1;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class Demo {

    @Test
    public void test01() {
        CoffeeSupplier coffeeSupplier = new CoffeeSupplier(6);
        Iterator<Coffee> iterator = coffeeSupplier.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test02() {
        CoffeeSupplier coffeeSupplier = new CoffeeSupplier(5);
        // CoffeeSupplier 实现了Iterable 接口可以使用 for-in 遍历
        for (Coffee coffee : coffeeSupplier) {
            System.out.println(coffee);
        }
    }


}
