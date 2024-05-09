package com.guorong.demo.stock;

import com.guorong.demo.entity.DbStock;
import com.guorong.demo.mapper.DbStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    @Autowired
    private DbStockMapper dbStockMapper;

    // @Transactional(rollbackFor = Exception.class)
    public synchronized void reduceStock() {
        DbStock dbStock = dbStockMapper.selectCustomById(1L);
        dbStock.setCount(dbStock.getCount() -1);
        dbStockMapper.updateById(dbStock);
        System.out.println(dbStock.getProductCode() + " 库存余量: " + dbStock.getCount());
    }
}
