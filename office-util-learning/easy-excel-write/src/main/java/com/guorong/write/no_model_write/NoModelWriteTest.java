package com.guorong.write.no_model_write;

import com.alibaba.excel.EasyExcel;
import com.guorong.write.util.ClassPathUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 不创建对象模型写
 */
class NoModelWriteTest {



    /**
     * 不创建对象的写
     */
    @Test
    void noModelWrite_01() {
        // 写法1
        String fileName = ClassPathUtils.getClassPath()
                .concat("noModelWrite")
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".xlsx");

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName)
                .head(head())
                .sheet("无模型sheet")
                .doWrite(dataList());
    }

    private List<List<String>> head() {
        List<String> head1 = Arrays.asList("字符串");
        List<String> head2 = Arrays.asList("日期");
        List<String> head3 = Arrays.asList("数字");
        // 添加请求头
        return Arrays.asList(head1, head2, head3);
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 1; i < 5; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(i);
            list.add(data);
        }
        return list;
    }
}
