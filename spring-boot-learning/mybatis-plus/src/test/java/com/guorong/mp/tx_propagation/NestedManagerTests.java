package com.guorong.mp.tx_propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class NestedManagerTests {

    @Autowired
    private NestedManager service;

    @Autowired
    private PropagationService propagationService;

    // --------------------------------------------------------------------------

    @Test
    public void test_no_transaction_exception_nested_nested() {
        try {
            service.no_transaction_exception_nested_nested();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNotNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNotNull(propagationService.getById(2L));
    }

    @Test
    public void test_no_transaction_nested_nested_exception() {
        try {
            service.no_transaction_nested_nested_exception();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNotNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNull(propagationService.getById(2L));
    }


    @Test
    public void test_transaction_exception_nested_nested() {
        try {
            service.transaction_exception_nested_nested();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNull(propagationService.getById(2L));
    }


    @Test
    public void test_transaction_nested_nested_exception() {
        try {
            service.transaction_nested_nested_exception();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNull(propagationService.getById(2L));
    }

    @Test
    public void test_transaction_nested_nested_exception_try() {
        try {
            service.transaction_nested_nested_exception_try();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNotNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNull(propagationService.getById(2L));
    }
}
