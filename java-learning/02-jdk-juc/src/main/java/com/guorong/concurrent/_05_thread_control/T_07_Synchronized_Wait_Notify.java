package com.guorong.concurrent._05_thread_control;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * wait()等待, notify()唤醒
 */
class T_07_Synchronized_Wait_Notify {


    // 大炮类
    private static class Artillery {

        // 炮弹
        private LinkedList<String> bullets = new LinkedList<>();

        // 最大容量
        private static final int MAX_SIZE = 5;

        // 装弹
        public void inBullet() {
            try {
                while (true) {
                    synchronized (this) {

                        while (bullets.size() >= MAX_SIZE) {
                            this.notifyAll();
                            this.wait();
                        }

                        bullets.addFirst("炮弹");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName() + ": 装弹成功...当前弹药数量=" + bullets.size());
                        this.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        // 发炮
        public void outBullet() {
            try {
                while (true) {
                    synchronized (this) {

                        while (bullets.size() == 0) {
                            this.notifyAll();
                            this.wait();
                        }

                        bullets.removeLast();
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName() + ": 发炮成功...当前弹药数量=" + bullets.size());
                        this.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    public static void main(String[] args) {
        Artillery artillery = new Artillery();

        for (int i = 1; i < 6; i++) {
            new Thread(artillery::inBullet, "装弹线程-" + i).start();
            new Thread(artillery::outBullet, "发炮线程-" + i).start();
        }


    }


}
