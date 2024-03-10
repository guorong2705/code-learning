package com.guorong.generic.generic_method_2;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author guorong
 * @date 2021-08-14
 */
public class Demo {

    @Test
    public void test01() {
        Supplier<ObjectBean> supplier = BasicSupplier.create(ObjectBean.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(supplier.get());
        }
    }

    @Test
    public void test02() {
        Supplier<ObjectBean> supplier = BasicSupplier.create(ObjectBean.class);
        Stream.generate(supplier)
                .limit(5)
                .forEach(System.out::println);
    }


}
