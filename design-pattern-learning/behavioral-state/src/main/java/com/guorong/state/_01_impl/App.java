package com.guorong.state._01_impl;

/**
 * 演示状态模式
 */
class App {
    public static void main(String[] args) {
        int count = 10;
        // 糖果机
        GumballMachine gumballMachine = new GumballMachine(count);
        System.out.println(gumballMachine);
        for (int i = 0; i < count; i++) {
            // 放入硬币，并转动曲柄
            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();
            System.out.println(gumballMachine);
        }
    }
}
