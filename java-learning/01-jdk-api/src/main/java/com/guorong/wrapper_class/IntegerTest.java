package com.guorong.wrapper_class;

import org.junit.jupiter.api.Test;

/**
 *
 * @author guorong
 * @date 2021-07-31
 */
public class IntegerTest {

    /**
     * 测试缓存池
     */
    @Test
    public void testCachePool() {
        Integer value01 = Integer.valueOf(10);
        Integer value02 = Integer.valueOf(10);
        Integer value03 = new Integer(10);
        Integer value04 = 10;

        // valueOf() 方法会从缓存池中获取
        System.out.println(value01 == value02); // true

        // new Integer(); 会重新创建一个对象不会从缓存池中获取
        System.out.println(value01 == value03); // false

        // Integer value04 = 10; 自动装箱，编译器编译后为： Integer value04 = Integer.valueOf(10)
        System.out.println(value01 == value04); // true
    }





}
