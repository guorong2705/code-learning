package com.guorong.command._01_impl;

/**
 * 命令的接收者角色 - 电视机
 */
class Television{

    /**
     * 所在位置
     */
    private String location;


    public Television(String location) {
        this.location = location;
    }

    /**
     * 开机
     */
    public void on() {
        System.out.println(location + "电视开机了....");
    }

    /**
     * 关机
     */
    public void off() {
        System.out.println(location + "电视关机了....");
    }


}
