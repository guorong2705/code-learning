package com.guorong.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Book implements Serializable {
    private Long id;
    private String bookName;
    private BigDecimal price;
}
