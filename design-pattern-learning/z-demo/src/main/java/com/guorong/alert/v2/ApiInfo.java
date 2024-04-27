package com.guorong.alert.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class ApiInfo {
    private long requestCount;
    private long durationOfSeconds;
    private long errorCount;
}
