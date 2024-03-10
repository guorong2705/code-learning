package com.guorong.hutool.util.date;

import cn.hutool.core.date.*;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * 日期时间工具-DateUtil
 *
 * @author 80006000
 */
public class DateUtilTest {


    /**
     * 获取当前时间
     */
    @Test
    public void currentDate() {
        // 当前时间
        Date date1 = DateUtil.date();
        // 当前时间
        Date date2 = DateUtil.date(System.currentTimeMillis());
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();
    }

    /**
     * 字符串转日期:
     * DateUtil.parse方法会自动识别一些常用格式
     */
    @Test
    public void parse() {
        // 识别常用格式
        String strDate = "2012/12/12 12:12:12";
        Date parse1 = DateUtil.parse(strDate);
        // 自定义日期格式转化
        DateTime parse2 = DateUtil.parse(strDate, "yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 格式化日期输出
     */
    @Test
    public void format() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);

        // 结果 2017/03/01
        String format = DateUtil.format(date, "yyyy/MM/dd");

        // 常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date);

        // 结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date);

        // 结果：00:00:00
        String formatTime = DateUtil.formatTime(date);
    }

    /**
     * 获取Date对象的某个部分:年、月、日 等
     */
    @Test
    public void test01() {
        Date date = DateUtil.parse("2021-11-12 12:12:12");
        // 年: 2021
        System.out.println("年: " + DateUtil.year(date));
        // 月: 10
        System.out.println("月: " + DateUtil.month(date));
        // 月中日: 12
        System.out.println("月中日: " + DateUtil.dayOfMonth(date));
        // 年中日: 316
        System.out.println("年中日: " + DateUtil.dayOfYear(date));
        // 这个月的结束：2021-11-30 23:59:59
        System.out.println("一个月的结束：" + DateUtil.endOfMonth(date));
    }


    /**
     * 日期时间偏移
     */
    @Test
    public void offset() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        // 天数偏移: 结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);

        // 常用天数偏移: 结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);

        //常用小时偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);
    }

    /**
     * 日期时间差
     */
    @Test
    public void between() {
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 23:55:23";
        Date date2 = DateUtil.parse(dateStr2);
        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        System.out.println(betweenDay);

        // 格式化时间差: Level.MINUTE表示精确到分
        String formatBetween = DateUtil.formatBetween(date1, date2, BetweenFormatter.Level.MINUTE);
        System.out.println(formatBetween);
    }


    /**
     * 获取某个月份的最后一天
     */
    @Test
    public void endOfMonth() {
        Date date = new Date();
        DateTime dateTime = DateUtil.endOfMonth(date);
        System.out.println(dateTime.toString());
    }

}






















