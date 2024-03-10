package com.guorong.springboot.jdbc.service;

import com.guorong.springboot.jdbc.entity.Order;

/**
 * 玉米饼订单 业务逻辑
 *
 * @author guorong
 * @date 2020-05-18
 */
public interface OrderService {


    /**
     * 保存玉米饼订单
     *
     * @param order
     */
    void saveOrder(Order order);
}
