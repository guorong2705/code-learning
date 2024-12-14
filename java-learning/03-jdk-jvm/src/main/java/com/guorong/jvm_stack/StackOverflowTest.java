package com.guorong.jvm_stack;

/**
 * 虚拟机栈溢出测试
 */
class StackOverflowTest {

    /**
     * 设置虚拟机栈大小：-Xss128k
     * @param args
     */
    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("count: " + count);
        }
    }

    static int count = 0;
    static void redo() {
        count ++;
        redo();
    }


}
