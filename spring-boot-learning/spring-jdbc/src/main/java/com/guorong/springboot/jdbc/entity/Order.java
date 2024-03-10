package com.guorong.springboot.jdbc.entity;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 玉米饼的订单
 *
 * @author guorong
 * @date 2020-05-14
 */
@Data
public class Order {

    /**
     * 主键
     */
    private Long id;

    /**
     * 订单用户名
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * 街道
     */
    @NotBlank(message = "Street is required")
    private String street;

    /**
     * 城市
     */
    @NotBlank(message = "City is required")
    private String city;

    /**
     * 美国的州: 例如：纽约州
     */
    @NotBlank(message = "State is required")
    private String state;

    /**
     * 邮编
     */
    @NotBlank(message = "Zip is required")
    private String zip;

    /**
     * 信用卡号码
     */
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    /**
     * 信用卡有效期
     */
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    /**
     * 信用卡安全码
     */
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    /**
     * 下订单的日期和时间
     */
    private Date createdTime;


    /**
     * 该订单的 玉米饼
     */
    List<Taco> tacoList = new ArrayList<>();
}
