package com.guorong.class_06;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 问题描述：
 * random指针式单链表节点结构中新增的指针，random可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 要求：时间复杂度O(N)，额外空间复杂度O(1)
 */
class LinkedListCopyWithRandom {

    // 一种特殊的单链表节点：random随机指向某个节点
    private static class Node {
        private int value;
        private Node next;
        private Node random;

        public Node(int value) {
            this.value = value;
        }
    }

    // 1.使用哈希表，这种方式的时间复杂度是 O(n)，但空间复杂度也是 O(n)。
    Node copyByHashMap(Node head) {
        if (head == null) {
            return null;
        }
        // 存储原始节点和复制节点的内存地址映射
        Map<Node, Node> map = new HashMap<>();
        // 复制所有节点
        for (Node curr = head; curr != null; curr = curr.next) {
            map.put(curr, new Node(curr.value));
        }
        // 构建 next 和 random 指针
        for (Node curr = head; curr != null; curr = curr.next) {
            Node cloned = map.get(curr);
            cloned.next = map.get(curr.next);
            cloned.random = map.get(curr.random);
        }
        return map.get(head);
    }

    @Test
    void testCopyByHashMap() {
        Node head = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        head.next = n1;
        n1.next = n2;
        head.random = n2;
        Node copyHead = copyByHashMap(head);
    }
    // ==============================================================

    // 2.不使用额外空间: 时间复杂度O(n)，额外空间复杂度O(1)
    Node copyNode(Node head) {
        if (head == null) {
            return null;
        }
        // 复制链表节点并连接在原节点后面: n1->n1`->n2->n2`
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = new Node(curr.value);
            curr.next.next = next;
            curr = next;
        }
        // 处理随机指针
        curr = head;
        while (curr != null) {
            Node nextNode = curr.next.next; // 下一对节点
            Node copyNode = curr.next;
            copyNode.random = curr.random != null ? curr.random.next : null;
            curr = nextNode; // 移动到下一个原始节点
        }
        // 拆分原链表和复制链表
        Node copyHead = head.next;
        curr = head;
        while (curr != null) {
            Node nextNode = curr.next.next; // 下一对节点
            Node copyNode = curr.next;
            curr.next = nextNode; // 关联下一个原节点
            copyNode.next = nextNode != null ? nextNode.next : null;
            curr = nextNode;
        }
        return copyHead;
    }

    @Test
    void testCopyNode() {
        Node head = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        head.next = n1;
        n1.next = n2;
        head.random = n2;
        Node newHead = copyNode(head);
    }

}
