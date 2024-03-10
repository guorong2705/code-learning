package com.guorong.mybatis.mapper;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * @author guorong
 * @date 2020-10-04
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DruidDataSourceTests {

    @Autowired
    private DataSource dataSource;


    @Test
    public void test() {
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        int maxActive = druidDataSource.getMaxActive();
        log.info("maxActive = {}", maxActive);
    }


}
