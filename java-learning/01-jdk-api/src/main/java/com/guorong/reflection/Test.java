package com.guorong.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        Creator creator = new ForNameCreator();
        Stream<Animal> stream = creator.stream();
        stream.forEach(System.out::println);
    }
}


abstract class Creator implements Supplier<Animal> {

    private Random random = new Random(47);

    protected abstract List<Class<? extends Animal>> types();

    @Override
    public Animal get() {
        try {
            // 随机获取 Class 对象
            int index = random.nextInt(types().size());
            Class<? extends Animal> clazz = types().get(index);

            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<Animal> stream() {
        return Stream.generate(this)
                .limit(2);
    }
}

class ForNameCreator extends Creator {

    private static List<Class<? extends Animal>> types = new ArrayList<>();

    private static List<String> classNameList = Arrays.asList(
            Animal.CLASS_NAME,
            Dog.CLASS_NAME,
            Cat.CLASS_NAME
    );

    private static void loader() {
        for (String className : classNameList) {
            try {
                Class<? extends Animal> clazz = (Class<? extends Animal>) Class.forName(className);
                types.add(clazz);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static {
        loader();
    }

    @Override
    protected List<Class<? extends Animal>> types() {
        return types;
    }
}

class Animal {
    public static final String CLASS_NAME = Animal.class.getName();

    public Animal() {
    }
}

class Dog extends Animal {
    public static final String CLASS_NAME = Dog.class.getName();

    public Dog() {
    }
}

class Cat extends Animal {
    public static final String CLASS_NAME = Cat.class.getName();

    public Cat() {
    }
}