package com.guorong.observer.custom;

/**
 * 观察者接口
 * @author guorong
 */
interface Observer {

    /**
     * 更新
     * @param temperature 温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    void update(double temperature, double humidity, double pressure);

}
