package com.guorong.discount;

import com.guorong.PaymentContext;
import com.guorong.utils.ValidateUtils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 节假日折扣策略
 */
public class HolidayDiscountStrategy implements IDiscountStrategy {


    private static final List<DayOfWeek> HOLIDAY_CACHE;

    static {
        // 节假日集合，
        HOLIDAY_CACHE = new ArrayList<>();
        HOLIDAY_CACHE.add(DayOfWeek.SATURDAY);
        HOLIDAY_CACHE.add(DayOfWeek.SUNDAY);
    }

    /**
     * 策略优先级
     */
    private final Integer priority;

    /**
     * 折扣率
     */
    private final Double discountRate;

    /**
     * 开始时间点
     */
    private final Integer beginHourOfDay;

    /**
     * 结束时间点
     */
    private final Integer endHourOfDay;

    public HolidayDiscountStrategy(Integer priority, Double discountRate, Integer beginHourOfDay, Integer endHourOfDay) {
        boolean invalidCondition = Objects.isNull(priority) || Objects.isNull(discountRate)
                || Objects.isNull(beginHourOfDay) || Objects.isNull(endHourOfDay);
        ValidateUtils.validateArgument(invalidCondition, String.format("参数不能为空：priority=%s, discountRate=%s, beginHourOfDay=%s, endHourOfDay=%s",
                priority, discountRate, beginHourOfDay, endHourOfDay));
        this.priority = priority;
        this.discountRate = discountRate;
        this.beginHourOfDay = beginHourOfDay;
        this.endHourOfDay = endHourOfDay;
    }

    @Override
    public double applyDiscount(double amount) {
        return amount * (1 - discountRate);
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    @Override
    public boolean isAvailable(PaymentContext paymentContext) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        if (!HOLIDAY_CACHE.contains(dayOfWeek)) {
            return false;
        }
        // 时间段
        int hour = localDateTime.getHour();
        return hour >= beginHourOfDay && hour <= endHourOfDay;
    }
}
