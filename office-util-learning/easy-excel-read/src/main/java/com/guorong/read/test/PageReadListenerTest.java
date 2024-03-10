package com.guorong.read.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.guorong.read.entity.Person;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author guorong
 * @date 2022-01-07
 */
public class PageReadListenerTest {


    @Test
    public void test() {
        /**
         * 需要满足: 1: JDK8+; 2: EasyExcel since: 3.0.0-beta1
         */
        // 获取类路径下的文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("person.xlsx");
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        PageReadListener<Person> pageReadListener = new PageReadListener<Person>(dataList -> {
            dataList.forEach(System.out::println);
        });
        EasyExcel.read(inputStream, Person.class, pageReadListener).sheet().doRead();
    }
}
