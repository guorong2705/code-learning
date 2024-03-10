package com.guorong.observer.jdk;

import java.util.Observable;

/**
 * 天气数据
 * @author guorong
 */
class WeatherData extends Observable {


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
     * 测量改变: (气象数据改变时自动调用)
     */
    public void measurementChanged() {
        // 标记数据改变(父类Observable中的方法)
        setChanged();
        // 通知订阅的观察者(父类Observable中的方法)
        notifyObservers();

    }


    /**
     * 模拟天气数据改变，测量到(数据更新)，人为调用
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


    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }
}
