package com.guorong.read.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.guorong.read.dao.CarDaoImpl;
import com.guorong.read.dao.PersonDaoImpl;
import com.guorong.read.entity.Car;
import com.guorong.read.entity.Person;
import com.guorong.read.listener.CarListener;
import com.guorong.read.listener.PersonListener;
import org.junit.Test;

import java.io.InputStream;
import java.util.Objects;

/**
 * 读取多个 sheet
 * @author guorong
 * @date 2021-05-15
 */
public class MultipleSheetReadTest {


    /**
     * 读取全部 sheet (注意：每个sheet的结构是一样的)
     */
    @Test
    public void readAllSheet() {
        InputStream inputStream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream("sheet-data-same-structure.xlsx");
        // 读取全部sheet: 需要注意 PersonListener 的 doAfterAllAnalysed 会在每个sheet 读取完毕后调用一次
        EasyExcel.read(inputStream, Person.class, new PersonListener(new PersonDaoImpl())).doReadAll();
    }


    /**
     * 读取部分个sheet (注意：每个sheet结构都不一样)
     */
    @Test
    public void readMultiSheet() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("sheet-data-different-structure.xlsx");
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(inputStream).build();
            // 第1个sheet
            ReadSheet readSheet1 = EasyExcel
                    .readSheet(0)
                    .head(Person.class)
                    .registerReadListener(new PersonListener(new PersonDaoImpl()))
                    .build();
            // 第2个sheet
            ReadSheet readSheet2 = EasyExcel
                    .readSheet(1)
                    .head(Car.class)
                    .registerReadListener(new CarListener(new CarDaoImpl()))
                    .build();
            // 注意:一定要把 sheet1 和 sheet2 一起传进去，不然有个问题就是03版的 excel 会读取多次，浪费性能
            excelReader.read(readSheet1, readSheet2);
        } finally {
            if (Objects.nonNull(excelReader)) {
                excelReader.finish();
            }
        }
    }




}
