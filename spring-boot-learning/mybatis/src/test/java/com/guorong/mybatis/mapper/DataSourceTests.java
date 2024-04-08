package com.guorong.mybatis.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class DataSourceTests {

    @Autowired
    private DataSource masterDataSource;

    @Autowired
    private DataSource slaveDataSource;

    @Test
    public void test() {
        log.info("master DataSource type --->>> {}", masterDataSource.getClass().getName());
        log.info("slave DataSource type --->>> {}", slaveDataSource.getClass().getName());
    }
}
