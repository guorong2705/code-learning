package com.guorong.read.dao;

import java.util.List;

/**
 * @author guorong
 */
public interface BaseDao<T> {


    /**
     * 保存数据到数据库
     * @param list
     */
    default void saveData(List<T> list){

    }

}
