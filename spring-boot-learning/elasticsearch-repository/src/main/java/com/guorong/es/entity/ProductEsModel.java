package com.guorong.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author guorong
 * @date 2021-04-14
 */
@Data
@Document(indexName = "{es.product.index.name}")
public class ProductEsModel implements Serializable {

    /**
     * 商品id
     */
    @Id
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品分类
     */
    private String category;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 图片地址
     */
    private String images;

    public ProductEsModel() {
    }

    public ProductEsModel(Long id, String title, String category, Double price, String images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.images = images;
    }
}
