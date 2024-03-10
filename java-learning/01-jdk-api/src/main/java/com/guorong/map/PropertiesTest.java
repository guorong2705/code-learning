package com.guorong.map;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Objects;
import java.util.Properties;

@Getter
class PropertiesTest {
    private String name;
    private String skinColor;
    {
        // 读取配置文件属性
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("appConfig.properties");) {
            Properties p = new Properties();
            p.load(inputStream);
            for (Field field : this.getClass().getDeclaredFields()) {
                field.set(this, p.get(field.getName()));
            }
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PropertiesTest instance = new PropertiesTest();
        String format = String.format("name=%s  skinColor=%s", instance.getName(), instance.getSkinColor());
        System.out.println(format);

    }

}
