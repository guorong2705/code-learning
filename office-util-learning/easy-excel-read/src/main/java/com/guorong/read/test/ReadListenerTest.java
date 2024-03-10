package com.guorong.read.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.guorong.read.dao.PersonDaoImpl;
import com.guorong.read.entity.Person;
import com.guorong.read.listener.PersonListener;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author guorong
 * @date 2022-01-07
 */
public class ReadListenerTest {

    @Test
    public void test01() {
        // 获取类路径下的文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("person.xlsx");
        // 这里需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, Person.class, new ReadListener<Person>() {
            @Override
            public void invoke(Person person, AnalysisContext context) {
                System.out.println("解析一条记录----->>> " + person);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("全部解析完--------->>> ");
            }
        }).sheet().doRead();
    }


}
