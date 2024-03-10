package com.guorong.reference_type;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * 引用类型--虚引用:
 *      创建虚引用, 需要使用到引用队列, 用户无法获取虚引用所指向的对象,
 *      当垃圾回收器回收垃圾后, 虚引用所指向的对象会被添加到引用队列中,此时可以通过引用队列获取到对象.
 */
public class PhantomReferenceTests {

    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 引用队列
        ReferenceQueue<Person> queue = new ReferenceQueue<>();
        // 虚引用
        PhantomReference<Person> reference = new PhantomReference<Person>(new Person("张三"), queue);

        System.out.println("垃圾回收前: reference=" + reference.get());
        System.out.println("垃圾回收前: queue=" + queue.poll());

        System.out.println("=======================================");
        System.gc(); // 通知垃圾回收

        TimeUnit.SECONDS.sleep(2);

        System.out.println("垃圾回收后: reference=" + reference.get());

        System.out.println("垃圾回收后: queue=" + queue.poll());

    }


}
