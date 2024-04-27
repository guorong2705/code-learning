package com.guorong.alert.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 报警规则
 */
@Getter
@AllArgsConstructor
enum AlertRule {
    RULE_ONE(6, 8),
    RULE_TWO(5, 6),
    DEFAULT_RULE(5, 8),
    ;
    private final long maxTps;
    private final long maxErrorCount;

}
