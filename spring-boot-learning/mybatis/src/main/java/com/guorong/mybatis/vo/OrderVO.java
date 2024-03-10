package com.guorong.mybatis.vo;

import com.guorong.mybatis.entity.OrderItem;
import com.guorong.mybatis.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderVO implements Serializable {
    private Long id;
    private String orderNo;
    private List<OrderItem> orderItemList;

    private User user;
}
