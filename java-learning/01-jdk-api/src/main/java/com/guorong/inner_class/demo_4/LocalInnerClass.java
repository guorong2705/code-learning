package com.guorong.inner_class.demo_4;

/**
 * 局部内部类演示
 * @author guorong
 * @date 2021-08-10
 */
public class LocalInnerClass {

    private Integer num;

    // 对内部类对象进行向上转型
    public Destination destination(String message) {

        final class PCDestination implements Destination {

            private String message;

            public PCDestination(String message) {
                this.message = message;
            }

            @Override
            public String readMessage() {
                return message;
            }
        }
        // 返回内部类实例
        return new PCDestination(message);
    }


    public static void main(String[] args) {
        // 创建外部类对象
        LocalInnerClass localInnerClass = new LocalInnerClass();
        // 通过外部类提供的方法获取内部类对象多态方式的基类
        Destination destination = localInnerClass.destination("你好");
        // 调用基类方法
        System.out.println(destination.readMessage());
    }

}
