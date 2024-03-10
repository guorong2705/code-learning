package com.guorong.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 不做处理,只是获取小数据量的表格数据
 * @author guorong
 * @date 2022-01-07
 */
public class BaseSmallDataListener<T> extends AnalysisEventListener<T> {

    private List<T> dataList = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public List<T> getDataList() {
        return dataList;
    }
}
