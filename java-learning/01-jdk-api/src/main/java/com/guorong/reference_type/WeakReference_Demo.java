package com.guorong.reference_type;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 引用类型--弱引用, 只要遇到垃圾回收, 只有弱引用所指向的对象会被回收.
 */
class WeakReference_Demo {

    public static void main(String[] args) throws InterruptedException {
        // 弱引用
        WeakReference<Object> weakReference = new WeakReference<Object>(new Object());

        System.out.println("垃圾回收前: " + weakReference.get());

        // 通知垃圾回收
        System.gc();

        // 等待垃圾回收完成
        TimeUnit.SECONDS.sleep(3);

        System.out.println("垃圾回收后: " + weakReference.get());

    }


}
