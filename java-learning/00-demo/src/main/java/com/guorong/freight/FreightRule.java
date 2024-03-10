package com.guorong.freight;

import java.util.ArrayList;
import java.util.List;

public class FreightRule {
    private Long id;
    private List<RuleItem> ruleItemList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RuleItem> getRuleItemList() {
        return ruleItemList;
    }

    public void setRuleItemList(List<RuleItem> ruleItemList) {
        this.ruleItemList = ruleItemList;
    }
}
