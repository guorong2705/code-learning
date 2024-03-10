package com.guorong.inner_class.demo_2;

/**
 * @author guorong
 * @date 2021-08-10
 */
class Sequence {

    /**
     * 存储数据容器
     */
    private Object[] items;

    /**
     * 当前索引的位置
     */
    private int index = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    /**
     * 添加元素到数组中
     * @param element
     */
    public void add(Object element) {
        if (index < items.length) {
            items[index++] = element;
        }
    }

    /**
     * 获取选择器
     * @return
     */
    public Selector selector() {
        return new SequenceSelector();
    }

    /**
     * 内部类实现正序选择器
     */
    private class SequenceSelector implements Selector {

        private int cursor = 0;

        @Override
        public boolean end() {
            return cursor == items.length;
        }

        @Override
        public Object current() {
            return items[cursor];
        }

        @Override
        public void next() {
            if (cursor < items.length) {
                cursor++;
            }
        }
    }

    /**
     * 逆序选择器
     * @return
     */
    public Selector reverseSelector() {
        return new ReverseSelector();
    }

    /**
     * 内部类实现逆向选择器
     */
    public class ReverseSelector implements Selector {

        private int cursor = items.length-1;

        @Override
        public boolean end() {
            return cursor == -1;
        }

        @Override
        public Object current() {
            return items[cursor];
        }

        @Override
        public void next() {
            if (cursor > -1) {
                cursor--;
            }
        }
    }
}
