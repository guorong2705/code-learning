package com.guorong.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.guorong.read.dao.BaseDao;
import com.guorong.read.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Person 监听器
 * @author guorong
 */
public class PersonListener extends AnalysisEventListener<Person> {

    /**
     * 每隔 5 条存储数据库，实际使用中可以 3000 条, 然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    /**
     * 临时数据容器
     */
    private List<Person> dataList = new ArrayList<>(BATCH_COUNT);

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个 service。当然如果不用存储这个对象没用。
     */
    private BaseDao baseDao;

    public PersonListener(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    /**
     * 解析excel表头
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("解析表头开始=============================>>");
        headMap.entrySet().forEach(System.out::println);
    }

    /**
     * 每解析一条数据都会调用一次
     * @param Person
     * @param analysisContext
     */
    @Override
    public void invoke(Person Person, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据 =======>> " + Person.toString());
        // 保存解析一行excel的数据到临时数据容器
        dataList.add(Person);
        // 当临时数据容器的数量达到批处理数量时，保存数据库，并清理临时数据
        if (dataList.size() >= BATCH_COUNT) {
            // 保存数据
            baseDao.saveData(dataList);
            // 清理临时数据
            dataList.clear();
        }
    }


    /**
     * 所有数据解析完成了调用该方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("解析完全部数据------------------------------------------------------->>");
        if (dataList.size() > 0) {
            // 这里也要保存数据，确保最后遗留的数据也存储到数据库
            baseDao.saveData(dataList);
            // 清空临时数据容器
            dataList.clear();
        }
    }


}
