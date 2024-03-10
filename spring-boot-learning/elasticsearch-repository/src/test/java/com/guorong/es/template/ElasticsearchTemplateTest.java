package com.guorong.es.template;

import com.guorong.es.entity.ProductEsModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guorong
 * @date 2021-04-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTemplateTest {

    private static final Logger log = LoggerFactory.getLogger(ElasticsearchTemplateTest.class);

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private String indexName = "my_test_index-02";


    /**
     * 创建索引
     */
    @Test
    public void createIndex() {
        boolean result = elasticsearchOperations.indexOps(ProductEsModel.class).create();
        log.info("create index ->> {}", result);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() {
        boolean result = elasticsearchOperations.deleteIndex(indexName);
        log.info("delete index ->> {}", result);
    }


    @Test
    public void test() {

    }





}
