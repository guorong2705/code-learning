package com.guorong.constructor;

import lombok.Getter;

class Person {

    @Getter
    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age) {
        this(name);
        System.out.println("年龄： " + age);
    }
}
