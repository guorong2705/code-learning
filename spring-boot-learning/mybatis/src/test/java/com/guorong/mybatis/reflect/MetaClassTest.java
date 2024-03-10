package com.guorong.mybatis.reflect;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.junit.Test;

public class MetaClassTest {

    @Test
    public void test() {
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        MetaClass metaClass = MetaClass.forClass(Person.class, reflectorFactory);
        System.out.println(metaClass.hasGetter("id"));
    }

    static class Person {
        private String name;

        public String getId() {
            return "001";
        }
    }
}
