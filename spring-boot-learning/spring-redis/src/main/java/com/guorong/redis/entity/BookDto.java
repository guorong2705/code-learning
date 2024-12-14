package com.guorong.redis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guorong
 * @date 2020-10-11
 */
@Data
public class BookDto implements Serializable {

    private String id;

    private String bookName;

    private Double price;

}
