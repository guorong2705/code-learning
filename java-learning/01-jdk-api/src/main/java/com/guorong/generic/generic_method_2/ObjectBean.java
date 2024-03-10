package com.guorong.generic.generic_method_2;

/**
 * @author guorong
 * @date 2021-08-14
 */
class ObjectBean {

    // 统计对象个数
    private static long counter = 0;

    // 当前对象的 id
    private final long id = counter++;

    public ObjectBean() {
    }

    @Override
    public String toString() {
        return String.format("%s - %s", getClass().getSimpleName(), id);
    }
}
