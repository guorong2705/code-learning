package com.guorong.queue;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 先进先出（FIFO）描述了最典型的队列规则（queuing discipline）。
 * 队列规则是指在给定队列中的一组元素的情况下，确定下一个弹出队列的元素的规则。
 * 先进先出声明的是下一个弹出的元素应该是等待时间最长的元素。
 * -----------------------------------------------
 * 优先级队列声明下一个弹出的元素是最需要的元素（具有最高的优先级）。
 * 在 Java 5 中添加了 PriorityQueue ，以便自动实现这种行为。
 * 当在 PriorityQueue 上调用 offer() 方法来插入一个对象时，该对象会在队列中被排序。
 * 默认的排序使用队列中对象的自然顺序（natural order），但是可以通过提供自己的 Comparator 来修改这个顺序。
 * PriorityQueue 确保在调用 peek() ， poll() 或 remove() 方法时，获得的元素将是队列中优先级最高的元素。
 */
public class PriorityQueueDemo {


    /**
     * 默认的排序使用队列中对象的自然顺序
     */
    @Test
    public void testNaturalOrder() {

        Queue<Integer> queue = new PriorityQueue<>();

        queue.offer(Integer.valueOf(1));
        queue.offer(Integer.valueOf(5));
        queue.offer(Integer.valueOf(2));
        queue.offer(Integer.valueOf(3));

        int len = queue.size();
        for (int i = 0; i < len; i++) {
            System.out.println(queue.poll());
        }



    }


    /**
     * 自定义排序顺序
     */
    @Test
    public void testCustomOrder() {

        // 反序(与自然顺序相反)
        Queue<Integer> queue = new PriorityQueue<Integer>(Comparator.reverseOrder());

        queue.offer(Integer.valueOf(8));
        queue.offer(Integer.valueOf(7));
        queue.offer(Integer.valueOf(2));
        queue.offer(Integer.valueOf(3));


        int len = queue.size();
        for (int i = 0; i < len; i++) {
            System.out.println(queue.poll());
        }



    }




}
