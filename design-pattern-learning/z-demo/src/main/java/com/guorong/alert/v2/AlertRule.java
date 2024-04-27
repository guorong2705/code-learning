package com.guorong.alert.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class AlertRule {
    private final long maxTps;
    private final long errorCount;
}
