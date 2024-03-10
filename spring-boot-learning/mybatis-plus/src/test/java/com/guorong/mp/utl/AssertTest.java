package com.guorong.mp.utl;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class AssertTest {

    @Test
    public void test() {
        Assert.isTrue(1==2, "自定义异常");
        System.out.println("------");
    }
}
