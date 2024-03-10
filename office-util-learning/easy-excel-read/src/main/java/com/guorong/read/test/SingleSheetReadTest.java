package com.guorong.read.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.guorong.read.dao.PersonDaoImpl;
import com.guorong.read.entity.Person;
import com.guorong.read.listener.PersonListener;
import org.junit.Test;

import java.io.InputStream;
import java.util.Objects;

/**
 * 读取单个 sheet
 *
 * @author guorong
 */
public class SingleSheetReadTest {


    /**
     * 简单读取方式-01
     */
    @Test
    public void simpleRead01() {
        // 获取类路径下的文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("person.xlsx");
        // 这里需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, Person.class, new PersonListener(new PersonDaoImpl())).sheet().doRead();
    }


    /**
     * 简单读取方式-02
     */
    @Test
    public void simpleRead02() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("person.xlsx");
        ExcelReader excelReader = null;
        try {
            // 构建excel文件读取器
            excelReader = EasyExcel.read(inputStream, Person.class, new PersonListener(new PersonDaoImpl())).build();
            // 构建excel表格的sheet
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取指定sheet
            excelReader.read(readSheet);
        } finally {
            if (Objects.nonNull(excelReader)) {
                excelReader.finish();
            }
        }
    }

    /**
     * 简单读取方式-03 (设置标题的数量)
     */
    @Test
    public void simpleRead03() {
        // 获取类路径下的文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("person-head-row-number.xlsx");
        ExcelReader excelReader = null;
        try {
            // 构建excel文件读取器
            excelReader = EasyExcel.read(inputStream, Person.class, new PersonListener(new PersonDaoImpl())).build();
            // 构建excel表格的sheet
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            /**
             * 设置表头的数量
             * 0 - 此工作表没有标题，第一行就是数据
             * 1 - 此工作表有一行标题，第二行是数据（这个是默认值）
             * 2 - 此工作表有两行标题，第三行才是数据
             */
            readSheet.setHeadRowNumber(1);
            // 读取指定sheet
            excelReader.read(readSheet);
        } finally {
            if (Objects.nonNull(excelReader)) {
                excelReader.finish();
            }
        }
    }

}
