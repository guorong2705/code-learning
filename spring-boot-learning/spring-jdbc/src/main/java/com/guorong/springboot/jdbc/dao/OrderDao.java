package com.guorong.springboot.jdbc.dao;

import com.guorong.springboot.jdbc.entity.Order;

import java.util.List;

/**
 * 玉米饼订单
 *
 * @author guorong
 * @date 2020-05-18
 */
public interface OrderDao {

    /**
     * 插入订单的信息
     *
     * @param order
     * @return
     */
    Long insertOrderInfo(Order order);


    /**
     * 保存订单中的玉米饼
     *
     * @param orderId
     * @param tacoIdList
     */
    void insertTacosToOrder(Long orderId, List<Long> tacoIdList);


}
