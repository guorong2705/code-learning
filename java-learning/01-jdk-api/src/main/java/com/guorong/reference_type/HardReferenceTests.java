package com.guorong.reference_type;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * 引用类型--强引用:
 */
public class HardReferenceTests {

    private Object object = new Object(){
        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize");
        }
    };

    @SneakyThrows
    @Test
    public void test01() {
        System.out.println(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(object);
    }


}
