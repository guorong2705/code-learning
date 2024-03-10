package com.guorong.jvm_stack;

/**
 * 局部变量表
 * @author guorong
 */
public class LocalVariableTest {


    /**
     * 局部变量表的变量槽，存储一个32位的局部变量，double,long的64位变量，占用练个连续的变量槽
     */
    public void test01() {
        // 一个变量槽
        int a = 12;
        // 两个连续的变量槽
        long b = 23L;
        // 两个连续的变量槽
        double c = 20.23;
        // 一个变量槽
        byte d = 12;

    }



    /**
     * 栈帧中的局部变量表中的变量槽是可以重复利用的，如果一个局部变量过了其作用域，那么在其之后声明的新的局部变量，
     * 很有可能复用过期的局部变量表的槽位，从而达到节省资源的目的。
     */
    public void test02() {
        int a = 12;
        {
            int b = 0;
            b = a +1;
        }
        // 局部变量表的长度为3，因为b此处超出了作用域，所以c复用了b的局部变量表的变量槽
        int c = 23;
    }


}
