package com.guorong.date_time;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * @author guorong
 * @date 2020-04-10
 */
public class LocalTimeTest {


    /**
     * 时间类 (LocalTime):
     * 一天中的时间，比如13:45:20，可以使用 LocalTime 类表示。你可以使用 of 重载的两个工厂方法创建 LocalTime 的实例。
     * 第一个重载函数接收小时和分钟，第二个重载函数同时还接收秒。同 LocalDate 一样，LocalTime类也提供了一些getter方法访问这些变量的值。
     */
    @Test
    public void testGetLocalTime () {
        // 根据传入参数(时、分、秒)
        LocalTime localTime = LocalTime.of(12, 30, 10);

        // 获取当前时间
        LocalTime nowLocalTime = LocalTime.now();

        // 通过解析获取
        LocalTime parseLocalTime01 = LocalTime.parse("12:30:10", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime parseLocalTime02 = LocalTime.parse("12:30:10");
    }



    /**
     * 获取时间：时、分、秒
     */
    @Test
    public void testGetHourOrMinuteOrSecond() {
        // 创建指定 LocalTime
        LocalTime localTime = LocalTime.of(21 ,30, 25);

        // 获取 时
        int hour01 = localTime.getHour();
        int hour02 = localTime.get(ChronoField.HOUR_OF_DAY);

        // 获取 分
        int minute01 = localTime.getMinute();
        int minute02 = localTime.get(ChronoField.MINUTE_OF_HOUR);

        // 获取 秒
        int second01 = localTime.getSecond();
        int second02 = localTime.get(ChronoField.SECOND_OF_MINUTE);
    }



}
