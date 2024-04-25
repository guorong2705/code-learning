package com.guorong.springframwork.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static final Map<String, String> userMap = new HashMap<>();

    static {
        userMap.put("u001", "张三");
        userMap.put("u002", "李四");
        userMap.put("u003", "王五");
    }

    public String getUser(String uid) {
        return userMap.get(uid);
    }
}
