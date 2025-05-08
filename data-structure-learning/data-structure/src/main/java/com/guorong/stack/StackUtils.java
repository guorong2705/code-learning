package com.guorong.stack;

/**
 * 栈的工具类
 */
public final class StackUtils {

    private StackUtils() {
    }


    /**
     * 同步栈操作
     *
     * @param stack 要同步的栈实例
     * @param <T>   栈元素类型
     * @return 同步栈实例
     */
    public static <T> IStack<T> synchronizedStack(IStack<T> stack) {
        return new SynchronizedStack<>(stack);
    }


    /**
     * 实现安全栈(装饰器类)
     *
     * @param <T> 栈元素类型
     */
    private static class SynchronizedStack<T> implements IStack<T> {

        /**
         * 委托处理的类
         */
        private final IStack<T> delegate;

        public SynchronizedStack(IStack<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public synchronized void push(T element) {
            delegate.push(element);
        }

        @Override
        public synchronized T pop() {
            return delegate.pop();

        }

        @Override
        public synchronized T peek() {
            return delegate.peek();
        }

        @Override
        public synchronized boolean isEmpty() {
            return delegate.isEmpty();
        }

        @Override
        public synchronized boolean isFull() {
            return delegate.isFull();
        }

        @Override
        public synchronized int size() {
            return delegate.size();
        }

        @Override
        public synchronized void clear() {
            delegate.clear();
        }
    }
}
