package com.guorong.inner_class.demo_2;

/**
 * 选择器
 * @author guorong
 * @date 2021-08-10
 */
interface Selector {


    /**
     * 是否是最后一个
     * @return
     */
    boolean end();

    /**
     * 获取当前对象
     * @return
     */
    Object current();

    /**
     * 指针移到下一个
     */
    void next();
}
