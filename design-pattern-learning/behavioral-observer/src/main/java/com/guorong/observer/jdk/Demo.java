package com.guorong.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @author guorong
 */
class Demo {

    public static void main(String[] args) {
        // 可观察者
        Observable observable = new WeatherData();
        // 观察者(当前状况)
        Observer currentCondition = new CurrentCondition(observable);
        // 观察者(温度统计)
        Observer weatherCount = new WeatherCount(observable);

        // 更新数据
        WeatherData weatherData = (WeatherData) observable;
        weatherData.setMeasurement(20.1, 30.1, 40.1);
        weatherData.setMeasurement(40.1, 50.1, 60.1);

        // 取消订阅
        observable.deleteObserver(currentCondition);
        // 更新数据
        weatherData.setMeasurement(11.1, 22.1, 33.1);
    }


}
