package com.guorong.freight;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Order order = new Order("A01", "B01", "C01");
        Map<Long, RuleItem> longRuleItemMap = RegionUtils.ruleItemMap(order, ruleList());
        System.out.println(longRuleItemMap);
    }

    private static List<FreightRule> ruleList() {
        List<FreightRule> list = new ArrayList<>();
        FreightRule freightRule = new FreightRule();
        list.add(freightRule);
        freightRule.setId(1L);
        List<RuleItem> ruleItemList = freightRule.getRuleItemList();
        ruleItemList.add(new RuleItem(1L, 1L,"","","", "yes"));
        ruleItemList.add(new RuleItem(2L, 1L,"A01","B01","C06", "no"));
        ruleItemList.add(new RuleItem(3L, 1L,"A01","B01",null, "no"));
        ruleItemList.add(new RuleItem(4L, 1L,"A05",null,null, "no"));

        return list;
    }

}
