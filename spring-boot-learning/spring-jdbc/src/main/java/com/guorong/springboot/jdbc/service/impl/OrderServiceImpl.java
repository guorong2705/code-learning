package com.guorong.springboot.jdbc.service.impl;

import com.guorong.springboot.jdbc.dao.OrderDao;
import com.guorong.springboot.jdbc.entity.Order;
import com.guorong.springboot.jdbc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guorong
 * @date 2020-05-18
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public void saveOrder(Order order) {
        // 保存订单信息
        Long orderId = orderDao.insertOrderInfo(order);

        // 保存订单中的玉米饼
        List<Long> tacoIdList = order.getTacoList()
                .stream()
                .map(taco -> taco.getId())
                .collect(Collectors.toList());

        orderDao.insertTacosToOrder(orderId, tacoIdList);
    }
}
