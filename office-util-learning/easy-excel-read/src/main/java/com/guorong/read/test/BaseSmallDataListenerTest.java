package com.guorong.read.test;

import com.alibaba.excel.EasyExcel;
import com.guorong.read.entity.Person;
import com.guorong.read.listener.BaseSmallDataListener;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author guorong
 * @date 2022-01-07
 */
public class BaseSmallDataListenerTest {

    @Test
    public void test() {
        // 获取类路径下的文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("person.xlsx");
        // 这里需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        BaseSmallDataListener<Person> listener = new BaseSmallDataListener<>();
        EasyExcel.read(inputStream, Person.class, listener).sheet().doRead();
        List<Person> dataList = listener.getDataList();
        dataList.forEach(System.out::println);
    }
}
