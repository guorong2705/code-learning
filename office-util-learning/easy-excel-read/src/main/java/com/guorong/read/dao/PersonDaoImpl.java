package com.guorong.read.dao;

import com.guorong.read.entity.Person;

import java.util.List;

/**
 *
 * @author guorong
 */
public class PersonDaoImpl implements BaseDao<Person> {

    @Override
    public void saveData(List<Person> list) {
        System.out.println("==================插入数据库开始====================================");
        list.forEach(System.out::println);
    }
}
