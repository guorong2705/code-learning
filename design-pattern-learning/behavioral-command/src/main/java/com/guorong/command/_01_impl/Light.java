package com.guorong.command._01_impl;

/**
 * 电灯类 (接收者角色)
 */
class Light {

    /**
     * 所在位置
     */
    private String location;

    public Light(String location) {
        this.location = location;
    }

    /**
     * 开灯
     */
    public void on() {
        System.out.println(location + "开灯了....");
    }

    /**
     * 关灯
     */
    public void off() {
        System.out.println(location + "关灯了....");
    }

}
