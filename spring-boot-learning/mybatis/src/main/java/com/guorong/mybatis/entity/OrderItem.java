package com.guorong.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem implements Serializable {
    private Long id;
    private String orderNo;

    private String skuCode;

    private String skuName;

}
