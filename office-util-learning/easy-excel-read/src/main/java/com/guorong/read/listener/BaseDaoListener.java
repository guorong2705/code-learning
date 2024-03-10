package com.guorong.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.guorong.read.dao.BaseDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用操作
 *
 * @author guorong
 */
public class BaseDaoListener<T> extends AnalysisEventListener<T> {

    /**
     * 每隔 5 条存储数据库，实际使用中可以 3000 条, 然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    /**
     * 临时数据容器
     */
    private List<T> dataList = new ArrayList<>(BATCH_COUNT);

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个 service。当然如果不用存储这个对象没用。
     */
    private BaseDao<T> baseDao;

    public BaseDaoListener(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    // 每次解析完一行,调用一次
    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据 ---------------->>" + data);
        // 保存解析一行excel的数据到临时数据容器
        dataList.add(data);
        // 当临时数据容器的数量达到批处理数量时，保存数据库，并清理临时数据
        if (dataList.size() >= BATCH_COUNT) {
            baseDao.saveData(dataList); // 保存数据
            dataList.clear(); // 清理临时数据
        }
    }

    // 解析完后最后调用一次
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("解析完全部数据-------------------------------------->>");
        if (dataList.size() > 0) {
            // 这里也要保存数据，确保最后遗留的数据也存储到数据库
            baseDao.saveData(dataList);
            // 清空临时数据容器
            dataList.clear();
        }
    }

}
