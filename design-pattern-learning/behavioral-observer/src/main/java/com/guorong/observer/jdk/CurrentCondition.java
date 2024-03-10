package com.guorong.observer.jdk;


import java.util.Observable;
import java.util.Observer;

/**
 * 当前天气状况
 * @author guorong
 */
class CurrentCondition implements Observer, DisplayElement {

    /**
     * 温度
     */
    private double temperature;

    /**
     * 湿度
     */
    private double humidity;

    /**
     * 可观察对象
     */
    private Observable observable;

    /**
     * 获取可观察对象
     * @return
     */
    public Observable getObservable() {
        return observable;
    }


    public CurrentCondition(Observable observable) {
        this.observable = observable;
        // 注册观察者
        observable.addObserver(this);
    }


    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            // 更新温度
            this.temperature = weatherData.getTemperature();
            // 更新湿度
            this.humidity = weatherData.getHumidity();
            // 显示
            display();
        }

    }

    /**
     * 显示当前天气状况
     */
    @Override
    public void display() {
        System.out.println("当前天气状况：{ 温度: " + temperature + ", 湿度: " + humidity + " }");
    }



}
