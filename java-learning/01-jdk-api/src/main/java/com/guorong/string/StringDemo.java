package com.guorong.string;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * String演示
 *
 * @author guorong
 * @create 2019-11-30
 */
public class StringDemo {

    /**
     *
     */
    @Test
    public void test01(){
        String s = "world";
        String[] arr = s.split(""); // [w, o, r, l, d]
        System.out.println(Arrays.toString(arr));




    }


}
