package com.guorong.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultPage<T> implements Serializable {

    private long pageNum;

    private long pageSize;

    private long total;

    private List<T> records;

}
