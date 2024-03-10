package com.guorong.try_with_resource.demo_2;

import java.io.IOException;

/**
 * 类实现了 AutoCloseable 接口的 close() 方法，再try-with-resource时，会自动跳出
 */
class Reporter implements AutoCloseable {

    private String className = this.getClass().getSimpleName();

    public Reporter() {
        System.out.println("Constructor..." + className);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Close..." + className);
    }
}

class First extends Reporter{}

class Second extends Reporter{}


