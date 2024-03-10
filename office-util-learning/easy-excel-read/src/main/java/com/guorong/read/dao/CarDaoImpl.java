package com.guorong.read.dao;

import com.guorong.read.entity.Car;

import java.util.List;

/**
 *
 * @author guorong
 */
public class CarDaoImpl implements BaseDao<Car> {


    @Override
    public void saveData(List<Car> list) {
        System.out.println("==================插入数据库开始====================================");
        list.forEach(System.out::println);
    }
}
