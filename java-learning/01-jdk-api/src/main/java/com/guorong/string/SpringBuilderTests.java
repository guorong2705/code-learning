package com.guorong.string;

import org.junit.jupiter.api.Test;

public class SpringBuilderTests {

    @Test
    public void test() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(i);
        }
        sb.deleteCharAt(sb.length() -1);
        System.out.println(sb.toString());
    }
}
