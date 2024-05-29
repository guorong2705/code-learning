package com.guorong.mp.tx_propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class RequiresNewManagerTests {

    @Autowired
    private RequiresNewManager requiresNewManager;

    @Autowired
    private PropagationService propagationService;

    @Test
    public void test_no_transaction_exception_requiresNew_requiresNew() {
        try {
            requiresNewManager.no_transaction_exception_requiresNew_requiresNew();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNotNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNotNull(propagationService.getById(2L));
    }

    @Test
    public void test_no_transaction_requiresNew_requiresNew_exception() {
        try {
            requiresNewManager.no_transaction_requiresNew_requiresNew_exception();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNotNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNull(propagationService.getById(2L));
    }

    // ---------------------------------------------------------

    @Test
    public void test_transaction_exception_required_requiresNew_requiresNew() {
        try {
            requiresNewManager.transaction_exception_required_requiresNew_requiresNew();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNotNull(propagationService.getById(2L));
        // 查询王五
        Assertions.assertNotNull(propagationService.getById(3L));
    }

    @Test
    public void test_transaction_required_requiresNew_requiresNew_exception() {
        try {
            requiresNewManager.transaction_required_requiresNew_requiresNew_exception();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNotNull(propagationService.getById(2L));
        // 查询王五
        Assertions.assertNull(propagationService.getById(3L));
    }


    @Test
    public void test_transaction_required_requiresNew_requiresNew_exception_try() {
        try {
            requiresNewManager.transaction_required_requiresNew_requiresNew_exception_try();
        } catch (Exception e) {
            log.info("{}", e.getMessage());
        }
        // 查询张三
        Assertions.assertNotNull(propagationService.getById(1L));
        // 查询李四
        Assertions.assertNotNull(propagationService.getById(2L));
        // 查询王五
        Assertions.assertNull(propagationService.getById(3L));
    }

}
