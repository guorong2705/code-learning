package com.guorong.lambda;

/**
 * 递归的阶乘
 */
public class RecursiveFactorial {

    private static IntCall intCall;

    public static void main(String[] args) {
        // 阶层
        intCall = i -> i == 0? 1 : i*intCall.call(i - 1);

        for (int i = 0; i <= 10; i++) {
            System.out.println(intCall.call(i));
        }
    }

}


@FunctionalInterface
interface IntCall {

    int call(int i);
}
