package com.guorong.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 序列容器
 * @param <T>
 */
class Sequence<T> {

    // 容器
    protected List<T> storage = new ArrayList<>();

    // 添加方法
    public void add(T t) {
        storage.add(t);
    }

    public boolean remove(T t) {
        return remove(t);
    }

}
