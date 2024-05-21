package com.guorong.demo.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/reduce_stock")
    public String reduceStock() {
        stockService.reduceStock();
        return "减库存成功";
    }
}
