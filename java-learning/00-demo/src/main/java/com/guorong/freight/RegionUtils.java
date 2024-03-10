package com.guorong.freight;

import cn.hutool.core.util.StrUtil;

import java.util.*;

public class RegionUtils {

    public static Map<Long, RuleItem> ruleItemMap(Order order, List<FreightRule> ruleList) {
        String sendAreaStr = order.getProvinceCode() + order.getCityCode() + order.getCountyCode();
        Map<Long, RuleItem> ruleIdMap = new HashMap<>();
        for (FreightRule freightRule : ruleList) {
            Map<Integer, RuleItem> scoreMap = new HashMap<>();
            for (RuleItem ruleItem : freightRule.getRuleItemList()) {
                // 判断是否为默认的
                if ("yes".equals(ruleItem.getIsDefault())) {
                    scoreMap.put(0, ruleItem);
                    continue;
                }
                String areaCode = "";
                if (StrUtil.isNotBlank(ruleItem.getProvinceCode())) {
                    areaCode += ruleItem.getProvinceCode();
                }
                if (StrUtil.isNotBlank(ruleItem.getCityCode())) {
                    areaCode += ruleItem.getCityCode();
                }
                if (StrUtil.isNotBlank(ruleItem.getCountyCode())) {
                    areaCode += ruleItem.getCountyCode();
                }
                if (StrUtil.isNotBlank(areaCode) && StrUtil.startWith(sendAreaStr, areaCode)) {
                    scoreMap.put(areaCode.length(), ruleItem);
                }
            }
            RuleItem ruleItem = scoreMap.get(Collections.max(scoreMap.keySet()));
            ruleIdMap.put(freightRule.getId(), ruleItem);
        }
        return ruleIdMap;
    }
}
