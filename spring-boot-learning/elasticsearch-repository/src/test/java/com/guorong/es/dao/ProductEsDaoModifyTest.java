package com.guorong.es.dao;

import com.guorong.es.entity.ProductEsModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guorong
 * @date 2021-04-14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEsDaoModifyTest {

    private static final Logger log = LoggerFactory.getLogger(ProductEsDaoModifyTest.class);

    @Autowired
    private ProductEsDao productEsDao;


    /**
     * 新增
     */
    @Test
    public void save(){
        ProductEsModel productEsModel = new ProductEsModel();
        productEsModel.setId(5L);
        productEsModel.setTitle("一加手机");
        productEsModel.setCategory("手机");
        productEsModel.setPrice(3999.0);
        productEsModel.setImages("http://www.atguigu/hw.jpg");
        ProductEsModel result = productEsDao.save(productEsModel);
        log.info("add product ->> {}", result);
    }


    /**
     * 批量保存
     */
    @Test
    public void saveAll() {
        List<ProductEsModel> productEsModelList = new ArrayList<>();
        productEsModelList.add(new ProductEsModel(1L, "mi phone", "小米手机",1999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(2L, "huawei phone", "深圳手机",2999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(3L, "oppo phone", "手机",3999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(4L, "vivo phone", "手机",4999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(5L, "vivo phone plus", "手机",4999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(6L, "one plus phone", "手机",5999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(7L, "onekone", "手机",5999.0, "http://www.atguigu/hw.jpg"));

        Iterable<ProductEsModel> iterable = productEsDao.saveAll(productEsModelList);
        log.info("save product list ->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    @Test
    public void saveAll2() {
        List<ProductEsModel> productEsModelList = new ArrayList<>();
        productEsModelList.add(new ProductEsModel(8L, "A", "小米手机",1999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(9L, "C", "深圳手机",2999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(10L, "B", "手机",3999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(11L, "A", "手机",4999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(12L, "C", "手机",4999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(13L, "G", "手机",5999.0, "http://www.atguigu/hw.jpg"));
        productEsModelList.add(new ProductEsModel(7L, "F", "手机",5999.0, "http://www.atguigu/hw.jpg"));

        Iterable<ProductEsModel> iterable = productEsDao.saveAll(productEsModelList);
        log.info("save product list ->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    /**
     * 删除全部
     */
    @Test
    public void deleteAll() {
        log.info("delete all product ---->>");
        productEsDao.deleteAll();
        log.info("delete all product success ---->>");
    }

    /**
     * 根据id删除
     */
    @Test
    public void deleteById() {
        productEsDao.deleteById(7L);
    }

    @Test
    public void delete() {
        productEsDao.delete(new ProductEsModel(10L, null, null,5999.0, null));
    }



}
