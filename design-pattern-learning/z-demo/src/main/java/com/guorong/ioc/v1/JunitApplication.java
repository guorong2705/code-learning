package com.guorong.ioc.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class JunitApplication {
    private final static List<TestCase> TEST_CASE_LIST = new ArrayList<>();

    public static void registerCase(TestCase testCase) {
        if (Objects.isNull(testCase)) {
            throw new IllegalArgumentException("testCase is not null");
        }
        TEST_CASE_LIST.add(testCase);
    }

    public static void run() {
        TEST_CASE_LIST.stream().forEach(TestCase::run);
    }
}
