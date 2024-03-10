package com.guorong.inner_class.demo_1;

import org.junit.jupiter.api.Test;

/**
 * 可以在外围内中，提供返回内部类实例的方法
 */
public class GetInnerClassTest {

    @Test
    public void test01() {
        // 创建外围类实例
        Parcel parcel = new Parcel();
        // 获取内部类实例
        Parcel.Content content = parcel.getContent();
        System.out.println(content.getValue());

        // 这里会报错，因为内部类 Destination 使用 private 修饰，只能在外部类内部使用
        // Parcel.Destination destination = parcel.getDestination("消息");

        // 通过方法调用 私有 Destination 的 getMessage() 
        parcel.printDestination("消息");
    }

}

