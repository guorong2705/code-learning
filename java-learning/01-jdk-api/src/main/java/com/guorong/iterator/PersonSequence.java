package com.guorong.iterator;

import java.util.Iterator;

/**
 * Person序列容器
 */
public class PersonSequence extends Sequence<Person> {


    /**
     * 获取迭代器对象
     * @return
     */
    public Iterator<Person> iterator() {

        return new Iterator<Person>() {

            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < storage.size();
            }

            @Override
            public Person next() {
                return storage.get(cursor++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }



}
