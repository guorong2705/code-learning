package com.guorong.date_time;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * JDK_8 日期和时间 演示
 * @author guorong
 * @create 2019-11-25
 */
public class DateTimeTest {


    /**
     * 合并日期和时间: LocalDateTime
     * 这个复合类名叫 LocalDateTime,是 LocalDate 和 LocalTime 的合体。它同时表示了日期和时间，
     * 但不带有时区信息，你可以直接创建，也可以通过合并日期和时间对象构造
     */
    @Test
    public void testLocalDateTime() {
        /**
         *  直接创建LocalDateTime
         */
        LocalDateTime localDateTime1 = LocalDateTime.now();

        /**
         *  通过合并日期和时间对象构建
         */
        LocalDate date = LocalDate.of(2011, 3, 18);
        LocalTime time = LocalTime.of(12, 30, 18);
        LocalDateTime localDateTime = LocalDateTime.of(date, time);

        /**
         * 获取属性
         */
        int year = localDateTime.getYear(); // 2011
        int month = localDateTime.getMonthValue(); // 3
        int hour = localDateTime.getHour(); // 12
    }



    /**
     * 机器的日期和时间格式 (Instant):
     * 正如你已经在 LocalDate 及其他为便于阅读而设计的日期-时间类中所看到的那样，
     * Instant 类也支持静态工厂方法 now ，它能够帮你获取当前时刻的时间戳。我们想要特别强调一点，
     * Instant 的设计初衷是为了便于机器使用。它包含的是由秒及纳秒所构成的数字。
     * 所以，它无法处理那些我们非常容易理解的时间单位
     */
    @Test
    public void testInstant() {
        // 获取当前时间戳
        Instant start = Instant.now();

        try {
            // 休眠五秒
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 获取5秒后的时间戳
        Instant end = Instant.now();

        // 获取两者之间的距离时间
        Duration duration = Duration.between(start, end);
        long second = duration.getSeconds();// second = 5
    }



    /**
     * Duration (基于时间值的间隔)
     * Duration类表示秒或纳秒时间间隔，适合处理较短的时间，需要更高的精确性.
     */
    @Test
    public void testDuration() {
        /**
         *  Duration 类的静态工厂方法 between 可以创建两个 LocalTime 对象、两个 LocalDateTime 对象、两个 Instant 对象之间的 duration。
         * 但是不支持两个 LocalDate 对象创建，会抛出：UnsupportedTemporalTypeException
         */
        LocalTime start = LocalTime.of(12, 30, 28);
        LocalTime end = LocalTime.of(13, 40, 28);
        Duration duration = Duration.between(start, end);

        // 获取和转换单位
        long second = duration.getSeconds(); // 秒
        long hours = duration.toHours(); // 小时
        long minutes = duration.toMinutes(); // 分
    }



    /**
     * Period (基于日期值)
     * Period 类表示一段时间的年、月、日
     */
    @Test
    public void testPeriod() {
        LocalDate startDate = LocalDate.of(2015, 11, 12);
        LocalDate endDate = LocalDate.of(2016, 12, 20);
        Period period = Period.between(startDate, endDate);

        int years = period.getYears();// 1
        int months = period.getMonths();// 1
        int days = period.getDays();// 8
        System.out.println("years=" + years + ", months=" + months + ", days=" + days);
    }



    /**
     * 操纵日期
     */
    @Test
    public void testUpdate() {
        LocalDate localDate = LocalDate.of(2011, 3, 18);
        /**
         * (1) 设置属性:
         * 如果已经有一个 LocalDate 对象，想要创建它的一个修改版，最直接也最简单的方法是使用withAttribute方法。withAttribute方法会创建对象的一个副本，并按照需要修改它的属性。
         * 注意: 下面的这段代码中所有的方法都返回一个修改了属性的对象。它们都不会修改原来的对象.
         */
        // 设置年份和月份
        LocalDate localDate1 = localDate.withYear(2014);// 2014-03-18
        LocalDate localDate2 = localDate.withMonth(9);// 2014-09-18

        // 采用更通用的 with 方法能达到同样的目的，它接受的第一个参数是一个TemporalField对象
        LocalDate localDate3 = localDate.with(ChronoField.YEAR, 2016);// 2016-03-18


        /**
         * (2) 以相对方式修改 LocalDate 对象的属性, 通过 ChronoUnit 枚举我们可以非常方便地实现 TemporalUnit 接口。
         */
        // 月份加上3
        LocalDate localDate4 = localDate.plusMonths(3);// 2016-06-18
        // 年份减去1年
        LocalDate localDate5 = localDate.minusYears(1);// 2010-03-18
        // 使用 plus 通用方法 (年份加上2)
        LocalDate localDate6 = localDate.plus(2, ChronoUnit.MONTHS);// 2013-03-18
        // 使用 minus 通用方法 (月份减去1)
        LocalDate localDate7 = localDate.minus(1, ChronoUnit.MONTHS);// 2011-02-18
    }


    /**
     * ChronoUnit
     * 可用于在单个时间单位内测量一段时间，例如天数或秒。
     */
    @Test
    public void testChronoUnit() {
        /**
         * 获取两个LocalDate的间隔
         */
        LocalDate start = LocalDate.of(2016, 12, 20);
        LocalDate end = LocalDate.of(2019, 12, 23);
        // 计算两个日期之间相隔多少天
        long days = ChronoUnit.DAYS.between(start, end);// 1098天
        // 计算两个日期之间相隔多少月
        long months = ChronoUnit.MONTHS.between(start, end);// 36个月
        // 计算两个日期之间相隔多少年
        long years = ChronoUnit.YEARS.between(start, end);// 3年
    }


    /**
     * DateTimeFormatter（日期和时间格式）
     */
    @Test
    public void testDateTimeFormatter() {

        LocalDate date = LocalDate.of(2016, 12, 20);
        /**
         * 使用 DateTimeFormatter 自带的格式化
         */
        String dateStr1 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);// 2016-12-20

        /**
         * 使用自定义的格式化
         */
        DateTimeFormatter myMatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dateStr2 = date.format(myMatter); // 2016/12/20

        /**
         * 解析日期
         */
        LocalDate localDate1 = LocalDate.parse("2016-12-20", DateTimeFormatter.ISO_LOCAL_DATE);
    }




}
