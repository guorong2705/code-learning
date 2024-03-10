package com.guorong.observer.custom;

/**
 * 手动书写观察者模（主题对象 + 观察者对象）
 * @author guorong
 */
class Demo {

    public static void main(String[] args) {

        // 主题对象(天气数据)
        WeatherData weatherData = new WeatherData();
        // 当前天气状况(观察者对象)
        CurrentCondition currentCondition = new CurrentCondition(weatherData);
        // 统计天气(统计温度)
        WeatherCount weatherCount = new WeatherCount(weatherData);

        // 改变天气数据
        weatherData.setMeasurement(20.22, 50.55, 60.66);
        weatherData.setMeasurement(26.22, 56.55, 66.66);
    }
}
