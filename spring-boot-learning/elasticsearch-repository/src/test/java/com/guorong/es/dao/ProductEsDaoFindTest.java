package com.guorong.es.dao;

import com.guorong.es.entity.ProductEsModel;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guorong
 * @date 2021-04-14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEsDaoFindTest {

    private static final Logger log = LoggerFactory.getLogger(ProductEsDaoFindTest.class);

    @Autowired
    private ProductEsDao productEsDao;

    /**
     * 根据ID查询
     */
    @Test
    public void findById() {
        ProductEsModel productEsModel = productEsDao.findById(1L).get();
        log.info("findById result ->> {}", productEsModel.toString());
    }

    /**
     * 查询全部
     */
    @Test
    public void findAll() {
        Iterable<ProductEsModel> iterable = productEsDao.findAll();
        log.info("find all product ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    //=====================================单个条件========================================================

    /**
     * math query 和 term query 的区别:
     * match query 搜索时，会解析查询字符串，进行分词，然后查询，
     * term query 输入的查询内容是什么，就会按照什么去查询，并不会解析查询内容，对它分词。
     */
    @Test
    public void matchQuery01() {
        // 先分词，在进行匹配: 默认Operator.OR
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "phone vivo");
        Iterable<ProductEsModel> iterable = productEsDao.search(matchQueryBuilder);
        log.info("match query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    @Test
    public void matchQuery02() {
        // 先分词,在进行匹配:  AND(同时满足分词后的多个条件：此处为"phone", "vivo")
        MatchQueryBuilder matchQueryBuilder = QueryBuilders
                .matchQuery("title", "phone vivo")
                .operator(Operator.AND);
        Iterable<ProductEsModel> iterable = productEsDao.search(matchQueryBuilder);
        log.info("match query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    @Test
    public void matchQuery03() {
        // 先分词，在进行匹配: 默认Operator.OR , 设置匹配个数
        MatchQueryBuilder matchQueryBuilder = QueryBuilders
                .matchQuery("title", "phone vivo").operator(Operator.AND)
                .minimumShouldMatch("100%");
        Iterable<ProductEsModel> iterable = productEsDao.search(matchQueryBuilder);
        log.info("match query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    @Test
    public void matchQuery04() {
        // 进行操作: OR (默认为OR),匹配一个就可以
        MatchQueryBuilder matchQueryBuilder = QueryBuilders
                .matchQuery("title", "phone vivo")
                .operator(Operator.OR);
        Iterable<ProductEsModel> iterable = productEsDao.search(matchQueryBuilder);
        log.info("match query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    @Test
    public void termQuery01() {
        // 有空格匹配不上
        TermQueryBuilder termquerybuilder = QueryBuilders.termQuery("title", "oppo phone");
        Iterable<ProductEsModel> iterable = productEsDao.search(termquerybuilder);
        log.info("term query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    @Test
    public void termQuery02() {
        // 没有空格可以匹配上
        TermQueryBuilder termquerybuilder = QueryBuilders.termQuery("title", "onekone");
        Iterable<ProductEsModel> iterable = productEsDao.search(termquerybuilder);
        log.info("term query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }


    // =====================================多个条件==========================================================

    /**
     * 同时匹配 “title”和“price” 条件
     */
    @Test
    public void boolQuery01() {
        // 与文档匹配的查询，该文档与其他查询的布尔组合匹配
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 不分词匹配
        TermQueryBuilder titleTermQueryBuilder = QueryBuilders.termQuery("title", "onekone");
        boolQueryBuilder.must(titleTermQueryBuilder);
        // 价格匹配
        TermQueryBuilder priceTermQueryBuilder = QueryBuilders.termQuery("price", Double.valueOf(1999.0));
        boolQueryBuilder.must(priceTermQueryBuilder);

        Iterable<ProductEsModel> iterable = productEsDao.search(boolQueryBuilder);
        log.info("term query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    /**
     * 同时匹配 “title”和“price” 条件
     */
    @Test
    public void boolQuery02() {
        // 与文档匹配的查询，该文档与其他查询的布尔组合匹配
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 不分词匹配
        TermQueryBuilder titleTermQueryBuilder = QueryBuilders.termQuery("title", "onekone");
        boolQueryBuilder.must(titleTermQueryBuilder);
        // 价格匹配
        TermQueryBuilder priceTermQueryBuilder = QueryBuilders.termQuery("price", Double.valueOf(1999.0));
        boolQueryBuilder.must(priceTermQueryBuilder);
        // minimumShouldMatch(1) 不起作用：对 should()有效，对must()无效
        // boolQueryBuilder.minimumShouldMatch(1);

        Iterable<ProductEsModel> iterable = productEsDao.search(boolQueryBuilder);
        log.info("term query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }

    /**
     * 同时匹配 “title”和“price” 条件
     */
    @Test
    public void boolQuery03() {
        // 与文档匹配的查询，该文档与其他查询的布尔组合匹配
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 不分词匹配
        TermQueryBuilder titleTermQueryBuilder = QueryBuilders.termQuery("title", "onekone");
        boolQueryBuilder.should(titleTermQueryBuilder);
        // 价格匹配
        TermQueryBuilder priceTermQueryBuilder = QueryBuilders.termQuery("price", Double.valueOf(1999.0));
        boolQueryBuilder.should(priceTermQueryBuilder);
        // 不起作用：对 should()有效，对must()无效
        boolQueryBuilder.minimumShouldMatch(1);

        Iterable<ProductEsModel> iterable = productEsDao.search(boolQueryBuilder);
        log.info("term query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }


    // ============================================================================================================

    /**
     * 范围查询
     */
    @Test
    public void rangeQuery() {
        RangeQueryBuilder rangequerybuilder = QueryBuilders.rangeQuery("price").from(3000).to(5000);
        Iterable<ProductEsModel> iterable = productEsDao.search(rangequerybuilder);
        log.info("range query ---->>");
        iterable.forEach(productEsModel -> log.info(productEsModel.toString()));
    }


    /**
     * 统计文档个数
     */
    @Test
    public void count() {
        long count = productEsDao.count();
        log.info("count--->>> {}", count);
    }


    /**
     * 分页查询
     */
    @Test
    public void findAllPage() {
        // 查询第二页数据，每页显示2条记录
        PageRequest pageRequest = PageRequest.of(1, 2);
        Page<ProductEsModel> page = productEsDao.findAll(pageRequest);
        List<ProductEsModel> content = page.getContent();
        content.forEach(e -> log.info(e.toString()));
    }


    /**
     * 限制查询个数
     */
    @Test
    public void test() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(PageRequest.of(0, 5))
                .build();
        Page<ProductEsModel> page = productEsDao.search(searchQuery);
        List<ProductEsModel> content = page.getContent();
        content.forEach(e -> log.info(e.toString()));
    }


    /**
     * 根据指定字段排序
     */
    @Test
    public void testSort() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(PageRequest.of(0,30))
                .withSort(SortBuilders.fieldSort("title.keyword").order(SortOrder.ASC))
                .build();
        Page<ProductEsModel> page = productEsDao.search(searchQuery);
        List<ProductEsModel> content = page.getContent();
        content.forEach(e -> log.info(e.toString()));
    }


}
