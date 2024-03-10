package com.guorong.heap;

import org.junit.jupiter.api.Test;

/**
 * ---------------------------------对象内存分配演示----------------------------------------------------------
 * 1. 设置堆内存大小：-Xms20m  -Xmx20m 此处参数表示堆空间不可扩展
 * 2. 设置新生代的大小： -Xmn10
 * 3. 设置Eden区域和Survivor区域的比例：Eden空间和两个Survivor空间默认的比值为：8：1：1,
 * 可以通过设置 "-XX:SurvivorRation" 来调整空间比率：例如 -XX:SurvivorRation=8
 * 4. 开启收集器日志：-XX：+PrintGCDetails
 */
public class ObjectMemoryAllocationTest {

    public static final int _1MB = 1024 * 1024;

    static class PriorityEden {

        /**
         * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
         * 对象优先在 Eden区 分配：
         * 分配 allocation4 对象的语句时会发生一次 MinorGC，这次回收的结果是新生代6651KB变为148KB，
         * 而总内存占用量则几乎没有减少（因为 allocation1、allocation2、allocation3三个对象都是存活的，虚拟机几乎没有找到可回收的对象）。
         * 产生这次垃圾收集的原因是为 allocation4 分配内存时，发现 Eden 已经被占用了6MB，剩余空间已不足以分配 allocation4 所需的 4MB 内存，
         * 因此发生 MinorGC。垃圾收集期间虚拟机又发现已有的三个2MB大小的对象全部无法放入Survivor空间（Survivor空间只有1MB大小），所以只好通过分配担保机制提前转移到老年代去。
         */
        public static void main(String[] args) {
            byte[] allocation1 = new byte[_1MB * 2];
            byte[] allocation2 = new byte[_1MB * 2];
            byte[] allocation3 = new byte[_1MB * 2];
            // 分配 allocation4 内存空间的时候，发生了一次 Minor GC
            byte[] allocation4 = new byte[_1MB * 4];
        }
    }


    static class MaxObjectDirectOld {
        /**
         * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
         * 大对象直接进入老年代的：
         * HotSpot虚拟机提供了-XX：PretenureSizeThreshold参数，指定大于该设置值的对象直接在老年代分配，
         * 这样做的目的就是避免在Eden区及两个Survivor区之间来回复制，产生大量的内存复制操作。
         */
        public static void main(String[] args) {
            // allocation对象实例直接分配在老年代中
            byte[] allocation = new byte[4 * _1MB];
        }
    }


    static class LongTermSurvival {
        /**
         * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
         * 长期存活的对象将进入老年代：
         * HotSpot虚拟机中多数收集器都采用了分代收集来管理堆内存，那内存回收时就必须能决策哪些存活对象应当放在新生代，哪些存活对象放在老年代中。
         * 为做到这点，虚拟机给每个对象定义了一个对象年龄（Age）计数器，存储在对象头中。对象通常在 Eden 区里诞生，如果经过第一次 MinorGC 后仍然存活，
         * 并且能被 Survivor 容纳的话，该对象会被移动到 Survivor 空间中，并且将其对象年龄设为1岁。对象在Survivor区中每熬过一次MinorGC，年龄就增加1岁，
         * 当它的年龄增加到一定程度（默认为15），就会被晋升到老年代中。对象晋升老年代的年龄阈值，可以通过参数-XX：MaxTenuringThreshold设置
         */
        public static void main(String[] args) {
            // 什么时候进入老年代决定于 XX:MaxTenuring-Threshold设置
            byte[] allocation1 = new byte[_1MB / 4];
            byte[] allocation2 = new byte[4 * _1MB];
            byte[] allocation3 = new byte[4 * _1MB];
            allocation3 = null;
            allocation3 = new byte[4 * _1MB];
        }
    }




}
