package com.guorong.sort;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;

abstract class ArraySort {

    protected static final int TEST_CASE_COUNT = 10000;

    protected long begin;

    // 记录操作时间 ===========================================
    @BeforeEach
    protected void beforeEach(TestInfo testInfo) {
        String testMethodName = testInfo.getTestMethod().map(Method::getName).orElse("unknown");
        System.out.println("开始测试方法----" + testMethodName);
        begin = System.currentTimeMillis();
    }

    @AfterEach
    protected void afterEach(TestInfo testInfo) {
        String testMethodName = testInfo.getTestMethod().map(Method::getName).orElse("unknown");
        long time = System.currentTimeMillis() - begin;
        System.out.println("测试用时：" + time);
        System.out.println("结束测试方法----" + testMethodName);
    }


    /**
     * 数组排序对数器
     */
    protected static class ArraySortLogarithmic {

        /**
         * 排序比较器
         *
         * @param testCaseCount       测试案例次数
         * @param arraySortMethod 选择排序方法
         */
        public void runArraySort(int testCaseCount, Consumer<int[]> arraySortMethod) {
            int maxLen = 10000;
            int maxValue = 100;
            for (int i = 0; i < testCaseCount; i++) {
                int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
                // int[] array = {53, 59, 75, 12};
                int[] copyArray1 = Arrays.copyOf(array, array.length);
                int[] copyArray2 = Arrays.copyOf(array, array.length);
                // 排序
                arraySortMethod.accept(copyArray1);
                Arrays.sort(copyArray2);
                // 验证是否有效
                if (!Arrays.equals(copyArray1, copyArray2)) {
                    System.out.println("测试失败：第" + (i + 1) + "次失败");
                    System.out.println("输入数据：" + Arrays.toString(array));
                    System.out.println("排序数据：" + Arrays.toString(copyArray1));
                    System.out.println("参考数据：" + Arrays.toString(copyArray2));
                    return;
                }
            }
        }
    }
}
