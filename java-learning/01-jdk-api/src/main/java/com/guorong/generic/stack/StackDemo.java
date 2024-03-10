package com.guorong.generic.stack;

/**
 * @author guorong
 * @date 2021-08-13
 */
public class StackDemo {

    static class LinkedStack<T> {

        static class Node<U> {

            // 节点包含的元素
            U item;

            // 下一个节点
            Node<U> next;

            Node() {
                this.item = null;
                this.next = null;
            }

            Node(U item, Node<U> next) {
                this.item = item; // 设置当前节点的元素
                this.next = next; // 设置当前节点的下一个节点引用
            }

            // 判断是否是最后一个
            boolean end() {
                return this.item == null && this.next == null;
            }
        }

        // 栈顶
        private Node<T> top = new Node<>();

        // 压栈
        public void push(T item) {
            top = new Node<T>(item, top);
        }

        // 弹栈
        public T pop() {
            T result = top.item; // 获取当前栈顶的节点中的元素
            if (!top.end()) {
                top = top.next; // 替换栈顶节点为下一个节点
            }
            return result;
        }
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        for (int i = 1; i <= 10; i++) {
            stack.push(String.valueOf(i));
        }
        String element = null;
        while ((element = stack.pop()) != null) {
            System.out.println(element);
        }
    }


}
