package com.guorong.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一分页查询参数封装
 */
@Data
public class PageForm<T> implements Serializable {

    private int pageNum = 1;

    private int pageSize;

    private T form;

}
