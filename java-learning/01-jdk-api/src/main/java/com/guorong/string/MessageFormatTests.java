package com.guorong.string;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

class MessageFormatTests {

    @Test
    void test() {
        String template = "Hello, my name is {name} and I am {age} years old.";
        HashMap<String, Object> values = new HashMap<>();
        values.put("name", "Alice");
        values.put("age", 25);
        String message = MessageFormat.format(template, values);
        System.out.println(message); // 输出：Hello, my name is Alice and I am 25 years old.
    }

    @Test
    void test2() {
        String template = "Hello, my name is %(name)s and I am %(age)d years old.";
        Map<String, Object> values = new HashMap<>();
        values.put("name", "Alice");
        values.put("age", 25);

        String message = String.format(template, values);
        System.out.println(message); // 输出：Hello, my name is Alice and I am 25 years old.
    }

}
