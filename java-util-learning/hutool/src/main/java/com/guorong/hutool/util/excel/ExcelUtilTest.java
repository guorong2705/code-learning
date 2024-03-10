package com.guorong.hutool.util.excel;

import cn.hutool.core.annotation.Alias;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * excel工具类
 *
 * @author guorong
 * @date 2021-08-02
 */
public class ExcelUtilTest {


    /**
     * 不建议使用（List<Object> 行数据长度不是固定的）
     * 读取Excel中所有行和列，都用列表示
     * [姓名, 年龄, 生日]
     * [张三, 30, 2011-12-13 00:00:00]
     * [李四, 27, 2012-12-11 00:00:00]
     * [王五, 28, 1990-10-13 00:00:00]
     */
    @Test
    public void testRead01() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("excel.xlsx");
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> rowDataList = reader.read();
        rowDataList.forEach(System.out::println);
    }


    /**
     * 抽取指定列的数据
     * [姓名, 张三, 李四, 王五]
     */
    @Test
    public void testRead02() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("excel.xlsx");
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> rowDatalist = reader.read();
        List<Object> result = rowDatalist.stream().map(rowData -> rowData.get(0)).collect(Collectors.toList());
        System.out.println(result);
    }


    /**
     * 读取为Map列表，默认第一行为标题行，Map中的key为标题，value为标题对应的单元格值。
     * {姓名=张三, 年龄=30, 生日=2011-12-13 00:00:00}
     * {姓名=李四, 年龄=27, 生日=2012-12-11 00:00:00}
     * {姓名=王五, 年龄=28, 生日=1990-10-13 00:00:00}
     */
    @Test
    public void testReadAll() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("excel.xlsx");
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Map<String, Object>> mapList = reader.readAll();
        mapList.forEach(System.out::println);
    }

    @Data
    private class Person {
        @Alias("姓名")
        private String name;
        @Alias("年龄")
        private Integer age;
        @Alias("生日")
        private Date birthday;
    }


    /**
     * 读取封装到对象
     */
    @Test
    public void testReadAllByClass() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("excel.xlsx");
        List<Person> personList = ExcelUtil.getReader(inputStream).readAll(Person.class);
        personList.forEach(System.out::println);
    }


    /**
     * 读取指定的sheet
     */
    @Test
    public void testSheet() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("excel.xlsx");
        // 通过 sheet 编号获取
        int sheetIndex = 0;
        ExcelReader reader1 = ExcelUtil.getReader(inputStream, sheetIndex);
        // 通过 sheet 名获取
        String sheetName = "人类";
        ExcelReader reader2 = ExcelUtil.getReader(inputStream, sheetName);
    }


    /**
     * 读取大数据量的Excel
     */
    @Test
    public void test() {
        // Sax方式读取Excel行处理器
        RowHandler rowHandler = (sheetIndex, rowIndex, rowList) -> {
            System.out.println("sheetIndex=" + sheetIndex + "  rowIndex=" + rowIndex + "  rowList=" + rowList);
        };
        ExcelUtil.readBySax("excel.xlsx", 0, rowHandler);
    }
}
