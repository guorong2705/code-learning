package com.guorong.demo;

/**
 * 栈深度测试：
 * Java虚拟机提供了参数-Xss来指定线程的最大栈空间，这个参数也直接决定了函数调用的最大深度。
 */
class StackDeepTest {
    private static int count = 0;

    public static void recursion() {
        count++;
        recursion();
    }

    // -Xss128k
    public static void main(String[] args) {
        try {
            recursion();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("deep of calling = " + count);
        }
    }
}
