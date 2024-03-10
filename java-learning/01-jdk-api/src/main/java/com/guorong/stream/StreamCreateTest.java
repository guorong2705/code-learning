package com.guorong.stream;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 创建流对象
 *
 * @author guorong
 * @create 2019-12-03
 */
public class StreamCreateTest {


    /**
     * 由值生成流:
     * 使用静态方法 Stream.of(),通过显式值创建一个流,它可以接受任意数量的参数
     */
    @Test
    public void testOf() {
        // 元素为数值
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        // 元素为字符串
        Stream<String> stringStream = Stream.of("java", "on", "stream");
    }


    /**
     * 通过 Random 创建 IntStream
     */
    @Test
    public void testIntsWithRandom() {
        Random random = new Random(47);
        IntStream intStream = random.ints(5, 20);
    }


    /**
     * 通过 Random 创建 DoubleStream
     */
    @Test
    public void testDoublesWithRandom() {
        Random random = new Random(48);
        DoubleStream doubles = random.doubles(20, 30);
    }


    /**
     * 由数组生成流:
     * 静态方法 Arrays.stream()从数组创建一个流,它接受一个数组作为参数。
     */
    @Test
    public void testArraysStream() {
        // IntStream
        IntStream intStream = Arrays.stream(new int[]{1, 2, 3});
        // Stream<Integer>
        Stream<Integer> integerStream = Arrays.stream(new Integer[]{1, 2, 3});
        // DoubleStream
        DoubleStream doubleStream = Arrays.stream(new double[]{11.11, 22.22, 33.33});
        // Stream<Double>
        Stream<Double> stream = Arrays.stream(new Double[]{11.11, 22.22, 33.33});
    }


    /**
     * 由文件生成流:
     * Java 中用于处理文件等I/O操作的 NIO API（非阻塞 I/O）已更新，以便利用 Stream API。
     * java.nio.file.Files 中的很多静态方法都会返回一个流。
     */
    @Test
    public void testFilesFines() throws URISyntaxException, IOException {
        String filePath = getClass().getResource("/").getPath().concat("a.txt");
        Path path = new File(filePath).toPath();
        if (Files.exists(path)) {
            Stream<String> lines = Files.lines(path);
            lines.forEach(System.out::println);
        }
    }


    /**
     * 由函数生成流：创建无限流
     * Stream.iterate() 产生的流的第一个元素 是种子(iterate 方法的第一个参数)，然后将种子传递给方法(iterate方法的第二个参数)。
     * 方法运行的结果被添加到流中，并被存储起来，作为下次调用 iterate() 方法时的第一个参数，以此类推。
     */
    @Test
    public void testIterate() {
        // 获取元素：0,2,4,6,8
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .map(i -> String.valueOf(i + " "))
                .forEach(System.out::print);
    }


    /**
     * 由函数生成流：创建无限流
     * generate 方法也可让你按需生成一个无限流。但 generate不是依次对每个新生成的值应用函数的。它接受一个 Supplier<T> 类型的Lambda提供新的值。
     */
    @Test
    public void testGenerate01() {
        // 获取5个 [0,1) 的随机数
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }


    /**
     * 由函数生成流：创建无限流
     * generate 方法也可让你按需生成一个无限流。但 generate不是依次对每个新生成的值应用函数的。它接受一个 Supplier<T> 类型的Lambda提供新的值。
     */
    @Test
    public void testGenerate02() {
        // 获取6个元素
        Stream.generate(() -> Math.random()).limit(6).forEach(System.out::println);
    }


    /**
     * 流的建造者模式：
     * 在建造者模式中，首先创建一个 builder 对象，然后将创建流所需的多个信息传递给它，最后 builder 对象执行创建流的操作。
     */
    @Test
    public void testBuilder() {
        // 创建Stream的构建者
        Stream.Builder<String> builder = Stream.builder();
        // 添加元素
        for (int i = 0; i < 5; i++) {
            builder.add(String.valueOf(i));
        }
        Stream<String> stream = builder.build();
        stream.forEach(System.out::println);
    }


}
