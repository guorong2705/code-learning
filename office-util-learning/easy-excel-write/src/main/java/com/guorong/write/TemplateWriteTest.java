package com.guorong.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.guorong.write.handler.CustomSheetWriteHandler;
import org.junit.Test;

/**
 * excel模板写出测试
 *
 * @author guorong
 * @date 2021-08-02
 */
public class TemplateWriteTest {


    @ExcelIgnoreUnannotated//忽略掉不带注解的字段
    @HeadRowHeight(20)
    @ContentRowHeight(12)
    private class Person {

        @ColumnWidth(15)
        @ExcelProperty(value = "姓名")
        private String name;

        @ColumnWidth(15)
        @ExcelProperty(value = "年龄")
        private Integer age;

        @ColumnWidth(15)
        @ExcelProperty(value = "国家")
        private String country;

        private String unannotated;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getUnannotated() {
            return unannotated;
        }

        public void setUnannotated(String unannotated) {
            this.unannotated = unannotated;
        }
    }



    /**
     * 下拉，超链接等自定义拦截器（上面几点都不符合但是要对单元格进行操作的参照这个）
     * demo这里实现2点。1. 对第一行第一列的头超链接到:https://github.com/alibaba/easyexcel 2. 对第一列第一行和第二行的数据新增下拉框，显示 测试1 测试2
     * 1. 创建excel对应的实体对象 参照{@link `DemoData`}
     * 2. 注册拦截器 {@link `CustomCellWriteHandler`} {@link CustomSheetWriteHandler}
     */
    @Test
    public void customHandlerWrite() {
        String fileName = this.getClass().getResource("/").getPath()  + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel
                .write(fileName, Person.class)
                .registerWriteHandler(new CustomSheetWriteHandler())
                .sheet("模板")
                .doWrite(null);
    }

}
