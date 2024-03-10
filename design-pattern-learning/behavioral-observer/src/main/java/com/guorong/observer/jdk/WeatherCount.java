package com.guorong.observer.jdk;


import java.util.*;

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
    private List<Double> temperatureList = new ArrayList<>();

    /**
     * 天气数据
     */
    private Observable observable;


    public WeatherCount(Observable weatherData) {
        this.observable = weatherData;
        // 订阅(注册)观察者
        weatherData.addObserver(this);
    }



    @Override
    public void display() {

        DoubleSummaryStatistics statistics = temperatureList.stream().mapToDouble(d -> d).summaryStatistics();
        // 最低温度
        minTemperature = statistics.getMin();
        // 最高温度
        maxTemperature = statistics.getMax();
        // 平均温度
        averageTemperature = statistics.getAverage();

        System.out.println("天气统计: { 最低温度: " + minTemperature + ", 最高温度: " + maxTemperature + ", 平均温度: " + averageTemperature);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            // 添加更新值
            temperatureList.add(weatherData.getTemperature());
            // 显示
            display();
        }
    }
}
