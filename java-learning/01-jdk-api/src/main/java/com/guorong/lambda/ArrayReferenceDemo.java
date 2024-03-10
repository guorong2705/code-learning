package com.guorong.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 数组的引用演示
 * @author guorong
 * @create 2019-11-25
 */
public class ArrayReferenceDemo {


    private interface MyArray {
        String[] make(int length);
    }

    @Test
    public void test() {

        MyArray myArray = String[]::new;
        String[] strArray = myArray.make(12);
        System.out.println(strArray.length);

        for(int i = 0; i < strArray.length; i++){
            strArray[i] = "str";
        }

        System.out.println(Arrays.toString(strArray));
    }
}


