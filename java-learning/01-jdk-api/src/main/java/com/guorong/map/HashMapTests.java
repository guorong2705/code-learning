package com.guorong.map;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTests {

    private static final HashMap<String, String> DEFAULT_MAP = new HashMap<>();

    static {
        DEFAULT_MAP.put("01", "value-1");
        DEFAULT_MAP.put("03", "value-3");
        DEFAULT_MAP.put("02", "value-2");
    }

    @Test
    public void testEntry() {
        Set<Map.Entry<String, String>> entrySet = DEFAULT_MAP.entrySet();
        DEFAULT_MAP.containsValue("001");
    }


}
