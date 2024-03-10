package com.guorong.observer.custom;

import java.util.ArrayList;
import java.util.List;

/**
 * 天气数据
 *
 * @author guorong
 */
class WeatherData implements Subject {


    /**
     * 温度
     */
    private double temperature;

    /**
     * 湿度
     */
    private double humidity;

    /**
     * 气压
     */
    private double pressure;

    /**
     * 订阅的观察者列表
     */
    private List<Observer> observerList;


    public WeatherData() {
        observerList = new ArrayList<>();
    }

    /**
     * 注册观察者
     *
     * @param observer
     */
    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    /**
     * 移除观察者
     *
     * @param observer
     */
    @Override
    public void removeObserver(Observer observer) {
        // 获取订阅者index
        observerList.remove(observer);
    }

    /**
     * 通知订阅的观察者
     */
    @Override
    public void notifyObserver() {
        // 通知订阅的观察者
        for (Observer observer : observerList) {
            observer.update(temperature, humidity, pressure);
        }
    }


    /**
     * 测量改变: (气象数据改变时自动调用)
     */
    public void measurementChanged() {
        // 通知订阅的观察者
        notifyObserver();
    }


    /**
     * 模拟天气数据改变，测量到
     *
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void setMeasurement(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }
}
