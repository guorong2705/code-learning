package com.guorong.observer.custom;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * 天气统计: 温度统计(temperature)
 * @author guorong
 */
class WeatherCount implements Observer, DisplayElement {

    /**
     * 最低温度
     */
    private Double minTemperature;

    /**
     * 最高温度
     */
    private Double maxTemperature;

    /**
     * 平均温度
     */
    private Double averageTemperature;

    /**
     * 存储收掉的温度数据
     */
    private List<Double> temperatures = new ArrayList<>();

    /**
     * 天气数据
     */
    private Subject weatherData;


    public WeatherCount(Subject weatherData) {
        this.weatherData = weatherData;
        // 订阅(注册)观察者
        weatherData.registerObserver(this);
    }

    @Override
    public void update(double temperature, double humidity, double pressure) {
        // 存储温度数据
        temperatures.add(temperature);
        // 显示统计数据
        display();
    }

    @Override
    public void display() {

        DoubleSummaryStatistics statistics = temperatures.stream().mapToDouble(d -> d).summaryStatistics();
        // 最低温度
        minTemperature = statistics.getMin();
        // 最高温度
        maxTemperature = statistics.getMax();
        // 平均温度
        averageTemperature = statistics.getAverage();

        System.out.println("天气统计: { 最低温度: " + minTemperature + ", 最高温度: " + maxTemperature + ", 平均温度: " + averageTemperature);
    }
}
