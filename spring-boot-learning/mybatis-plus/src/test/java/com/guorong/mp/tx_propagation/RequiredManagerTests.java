package com.guorong.mp.tx_propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class RequiredManagerTests {

    @Autowired
    private RequiredManager service;

    @Autowired
    private PropagationService propagationService;

    @Test
    public void test_no_transaction_exception_required_required() {
        try {
            service.no_transaction_exception_required_required();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNotNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNotNull(propagationService.getById(2L));
    }

    @Test
    public void test_no_transaction_required_required_exception() {
        try {
            service.no_transaction_required_required_exception();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNotNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNull(propagationService.getById(2L));
    }

    //--------------------------------------------------------

    @Test
    public void test_transaction_exception_required_required() {
        try {
            service.transaction_exception_required_required();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNull(propagationService.getById(2L));
    }


    @Test
    public void test_transaction_required_required_exception() {
        try {
            service.transaction_required_required_exception();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNull(propagationService.getById(2L));
    }

    @Test
    public void test_transaction_required_required_exception_try() {
        try {
            service.transaction_required_required_exception_try();
        } catch (Exception e) {
            log.error("错误：", e);
        }
        // 查询张三
        Assertions.assertNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNull(propagationService.getById(2L));
    }


}
