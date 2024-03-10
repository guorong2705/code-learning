package com.guorong.elasticsearch.client;

import com.guorong.elasticsearch.client.model.Phone;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchOperations;


    @Test
    public void testCreateIndex() {
        boolean result = elasticsearchOperations.indexOps(Phone.class).create();
        // boolean result = elasticsearchOperations.createIndex(Phone.class);
        log.info("create index result={} --->>>", result);
    }


    @Test
    public void test() {

    }



}
