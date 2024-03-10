package com.guorong.observer.custom;

/**
 * 当前天气状况
 * @author guorong
 */
class CurrentCondition implements Observer, DisplayElement{

    /**
     * 温度
     */
    private double temperature;

    /**
     * 湿度
     */
    private double humidity;

    /**
     * 天气数据
     */
    private Subject weatherData;


    public CurrentCondition(Subject weatherData) {
        this.weatherData = weatherData;
        // 注册观察者
        weatherData.registerObserver(this);
    }


    @Override
    public void display() {
        System.out.println("当前天气状况：{ 温度: " + temperature + ", 湿度: " + humidity + " }");
    }


    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        // 显示天气状况
        display();
    }
}
