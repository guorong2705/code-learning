package com.guorong.read.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author guorong
 * @date 2022-01-08
 */
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {

    // 解析一行数据调用一次
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        System.out.println(data);
    }

    // 解析完调用一次
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public static void main(String[] args) {
        // 获取类路径下的文件
        InputStream inputStream = NoModelDataListener.class.getClassLoader().getResourceAsStream("person.xlsx");
        EasyExcel.read(inputStream, new NoModelDataListener()).sheet().doRead();
    }
}
