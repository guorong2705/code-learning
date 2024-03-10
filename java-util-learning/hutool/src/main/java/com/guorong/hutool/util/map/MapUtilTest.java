package com.guorong.hutool.util.map;

import cn.hutool.core.map.MapUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class MapUtilTest {

    @Test
    public void testBuilder01() {
        Map<String, String> map = MapUtil.<String, String>builder()
                .put("key-1", "value-1")
                .put("key-2", "value-2")
                .build();
        System.out.println(map);
    }


    @Test
    public void testBuilder02() {
        Map<String, String> map = MapUtil.<String, String>builder("key-1", "value-1").build();
        System.out.println(map);
    }



}
