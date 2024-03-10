package com.guorong.sentinel.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.guorong.sentinel.service.OrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private List<String> orderNoList = new ArrayList<String>();


    @SentinelResource(value = "OrderService:listOrderNo", fallback = "listOrderNoFallback", blockHandler = "listOrderNoBlockHandler")
    public List<String> listOrderNo() {
        return orderNoList;
    }

    public List<String> listOrderNoFallback() {
        log.info("方法降级了...fallback");
        return Collections.emptyList();
    }

    public List<String> listOrderNoBlockHandler() {
        log.info("方法降级了...blockHandler");
        return Collections.emptyList();
    }

    @PostConstruct
    public void initFlowRules() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule = new FlowRule();
        rule.setResource("OrderService:listOrderNo");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(2);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
