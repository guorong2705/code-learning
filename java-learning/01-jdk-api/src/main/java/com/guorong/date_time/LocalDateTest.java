package com.guorong.date_time;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * 开始使用新的日期和时间API时，你最先碰到的可能是LocalDate类。该类的实例是一个不变对象，只提供了简单的日期，并不含当天的时间信息。另外，它也不附带任何与时区相关的信息。
 * TemporalField 是一个接口，它定义了如何访问 temporal 对象某个字段的值(LocalDate实现Temporal接口)。
 * ChronoField 枚举实现了这一接口，所以你可以很方便地使用 get 方法得到枚举元素的值
 * @author guorong
 * @date 2020-04-10
 */
public class LocalDateTest {


    /**
     * 创建 LocalDate 对象
     */
    @Test
    public void testCreateLocalDate() {
        // 获取当前年月日
        LocalDate nowLocalDate = LocalDate.now();

        // 获取指定年月日
        LocalDate ofLocalDate = LocalDate.of(2019, 12, 21);

        // 默认解析格式：2007-12-03
        LocalDate parseLocalDate01 = LocalDate.parse("2019-12-23");

        // 指定解析格式
        LocalDate parseLocalDate02 = LocalDate.parse("2019.01.12", DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }


    /**
     * 获取：年、月、日
     */
    @Test
    public void test() {
        LocalDate date = LocalDate.now();

        /**
         * 获取年
         */
        int year01 = date.getYear();
        int year02 = date.get(ChronoField.YEAR);

        /**
         * 获取月
         */
        int monthValue01 = date.getMonthValue();
        int monthValue02 = date.getMonth().getValue();
        int monthValue03 = date.get(ChronoField.MONTH_OF_YEAR);


        /**
         * 获取日
         */
        int dayOfMonth = date.getDayOfMonth();
        int dayOfMonth01 = date.get(ChronoField.DAY_OF_MONTH);

        /**
         * 获取 星期几
         */
        int dayOfWeekValue02 = date.get(ChronoField.DAY_OF_WEEK);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekValue01 = dayOfWeek.getValue();
    }


}
