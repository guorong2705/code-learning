package com.guorong.read.test;

import com.alibaba.excel.EasyExcel;
import com.guorong.read.dao.CarDaoImpl;
import com.guorong.read.dao.PersonDaoImpl;
import com.guorong.read.entity.Car;
import com.guorong.read.entity.Person;
import com.guorong.read.listener.BaseDaoListener;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author guorong
 */
public class BaseDaoListenerTest {

    /**
     * 简单读取方式
     */
    @Test
    public void simpleRead01() {
        // 获取类路径下的文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("person.xlsx");
        // 这里需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, Person.class, new BaseDaoListener(new PersonDaoImpl())).sheet().doRead();
    }


    /**
     * 简单读取方式
     */
    @Test
    public void simpleRead02() {
        // 获取类路径下的文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("car.xlsx");
        // 这里需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, Car.class, new BaseDaoListener(new CarDaoImpl())).sheet().doRead();
    }

}
