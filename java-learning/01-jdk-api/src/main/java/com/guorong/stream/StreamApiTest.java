package com.guorong.stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream API 的演示
 * filter、map、limit可以连成一条流水线；collect 触发流水线执行并关闭它。可以连接起来的流操作称为中间操作,关闭流的操作称为终端操作
 *
 * @author guorong
 * @create 2019-12-02
 */
public class StreamApiTest {

    private List<Apple> appleList = Arrays.asList(
            new Apple("red", 150),
            new Apple("blue", 100),
            new Apple("blue", 120),
            new Apple("yellow", 180),
            new Apple("green", 170),
            new Apple("orange", 160),
            new Apple("orange", 160)
    );

    /**
     * peek()操作的目的是帮助调试，它允许你无修改地查看流中的元素
     */
    @Test
    public void testPeek() {
        appleList.stream()
                .peek(System.out::println)
                .mapToDouble(Apple::getWeight)
                .peek(System.out::println)
                .count();
    }

    /**
     * 将流转换为数组
     */
    @Test
    public void testToArray() {
        Apple[] apples = appleList.stream().toArray(Apple[]::new);
        Stream.of(apples).forEach(System.out::println);
    }

    /**
     * 过滤操作(filter)
     */
    @Test
    public void testFilter() {
        // 获取重量大于150的
        appleList.stream()
                .filter(apple -> apple.getWeight() > 150)
                .forEach(System.out::println);
    }


    /**
     * 去除重复元素(根据流所生成元素的hashCode和equals方法实现)
     */
    @Test
    public void testDistinctByObject() {
        appleList.stream()
                // 去除重复元素
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 去除重复元素(根据指定的字段进行去重)
     */
    @Test
    public void testDistinctByProperty() {
        System.out.println("===============使用流去重=====================");
        ArrayList<Apple> resultList = appleList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Apple::getColor))),
                        ArrayList::new
                )
        );
        resultList.forEach(System.out::println);

        System.out.println("================使用平常代码去重======================");
        TreeSet<Apple> treeSet = new TreeSet<>(Comparator.comparing(Apple::getColor));
        treeSet.addAll(appleList);
        treeSet.stream().forEach(System.out::println);
    }


    /**
     * 截短流(流支持 limit(n) 方法，该方法会返回一个不超过给定长度的流)
     */
    @Test
    public void testLimit() {
        appleList.stream()
                .limit(2)
                .forEach(System.out::println);

    }


    /**
     * 跳过元素 (流还支持 skip(n) 方法，返回一个扔掉了前 n 个元素的流)
     */
    @Test
    public void testSkip() {
        appleList.stream()
                .filter(apple -> apple.getWeight() > 150)
                .skip(2)
                .forEach(System.out::println);
    }


    /**
     * 映射
     */
    @Test
    public void testMap() {
        // 1.使用方法引用(获取指定属性的集合)
        Stream<String> stream = appleList.stream().map(Apple::getColor);

        // 2.mapToDouble(获取指定属性的集合)
        DoubleStream doubleStream = appleList.stream().mapToDouble(Apple::getWeight);
    }

    /**
     * 流的扁平化: flatMap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
     */
    @Test
    public void testFlatMap() {
        System.out.println("===============获取元素中的单词中的字母==========================");
        String[] array = {"Hello", "World"};
        Arrays.stream(array)
                // 将每个单词转换为由其字母构成的数组
                .map(s -> s.split(""))
                // 将各个生成流扁平化为单个流
                .flatMap(Arrays::stream)
                .map(e -> e + " ")
                .forEach(System.out::print);
    }



    /**
     * 匹配
     */
    @Test
    public void testMath() {

        /**
         * 1. allMatch(Predicate) ：如果流的每个元素根据提供的 Predicate 都返回 true 时，结果返回为 true。在第一个 false 时，则停止执行计算。
         *   判断是否全部苹果的颜色都为绿色
         */
        boolean allMatch = appleList
                .stream()
                .allMatch(apple -> Objects.equals(apple.getColor(), "green"));

        /**
         * 2. anyMatch(Predicate)：如果流中的任意一个元素根据提供的 Predicate 返回 true 时，结果返回为 true。
         *   判断是否存在颜色为绿色的苹果
         */
        boolean anyMatch = appleList
                .stream()
                .anyMatch(apple -> Objects.equals(apple.getColor(), "green"));

        /**
         * 3. noneMatch(Predicate)：如果流的每个元素根据提供的 Predicate 都返回 false 时，结果返回为 true。
         *   判断没有一个苹果颜色为 green: 不存在绿色苹果返回 true，存在绿色苹果返回false
         */
        boolean noneMatch = appleList
                .stream()
                .noneMatch(apple -> Objects.equals(apple.getColor(), "green"));
    }



    /**
     * 查找
     */
    @Test
    public void testFind() {
        Integer[] numbers = {1, 2, 3, 4, null, 6, 7};

        /**
         * 1. findFirst()：返回第一个流元素的 Optional，如果流为空返回 Optional.empty。findFirst() 无论流是否为并行化的，总是会选择流中的第一个元素。
         */
        Optional<Integer> findFirst = Arrays.stream(numbers).findFirst();
        System.out.println(findFirst); // Optional[1]

        /**
         * 2. findAny(：返回含有任意流元素的 Optional，如果流为空返回 Optional.empty。对于非并行流，findAny()会选择流中的第一个元素（即使从定义上来看是选择任意元素）
         */
        Optional<Integer> findAny = Arrays.stream(numbers).findAny();
        System.out.println(findAny); // Optional[1]

        /**
         * 3. 获取最后一个元素
         */
        Optional<Integer> lastNumber = Arrays.stream(numbers).reduce((n1, n2) -> n2);
        System.out.println(lastNumber); // Optional[7]
    }


    /**
     * 组合：reduce()
     * •  reduce(BinaryOperator)：使用 BinaryOperator 来组合所有流中的元素。因为流可能为空，其返回值为 Optional。
     * •  reduce(identity, BinaryOperator)：功能同上，但是使用 identity 作为其组合的初始值。因此如果流为空，identity 就是结果。
     * Lambda 表达式中的第一个参数 a 是 reduce() 中上一次调用的结果，而第二个参数 b 是从流传递过来的值。
     */
    @Test
    public void testReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        /**
         * 元素求和：
         * 存在初始值: 第一个参数为初始值 0
         */
        Integer sum1 = numbers.stream().reduce(0, (a, b) -> a + b);
        Integer sum2 = numbers.stream().reduce(0, (a, b) -> Integer.sum(a, b));
        Integer sum3 = numbers.stream().reduce(0, Integer::sum);

        /**
         * 元素求和：
         * 不存在初始值: 返回一个 Optional<Integer> 考虑流中没有任何元素的情况。
         * reduce() 操作无法返回其和，因为它没有初始值。这就是为什么结果被包裹在一个 Optional 对象里，以表明和可能不存在。
         */
        Optional<Integer> sum01 = numbers.stream().reduce((a, b) -> a + b);
        Optional<Integer> sum02 = numbers.stream().reduce((a, b) -> Integer.sum(a, b));
        Optional<Integer> sum03 = numbers.stream().reduce(Integer::sum);

        /**
         * 获取最大值:
         */
        Optional<Integer> max01 = numbers.stream().reduce((a, b) -> a > b ? a : b);
        Optional<Integer> max02 = numbers.stream().reduce(Integer::max);

        /**
         * 获取最小值:
         */
        Optional<Integer> min01 = numbers.stream().reduce(Integer::min);
    }


    /**
     * 统计
     */
    @Test
    public void testStatistics() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7};

        /**
         * 1. 统计元素个数
         */
        long count = Arrays.stream(numbers).count();

        /**
         * 2. 获取最大值：max(Comparator) 根据所传入的 Comparator 所决定的“最大”元素。
         */
        Optional<Integer> maxValue1 = Arrays.stream(numbers).max(Comparator.naturalOrder());
        // IntStream获取最大值
        IntStream intStream1 = Arrays.stream(numbers).mapToInt(Integer::intValue);
        OptionalInt maxValue2 = intStream1.max();

        /**
         * 3. 最小值: min(Comparator) 根据所传入的 Comparator 所决定的“最小”元素。
         */
        Optional<Integer> minValue1 = Arrays.stream(numbers).min(Comparator.naturalOrder());
        // IntStream获取最小值
        IntStream intStream2 = Arrays.stream(numbers).mapToInt(Integer::intValue);
        OptionalInt minValue2 = intStream1.min();

        /**
         * 4.平均值： average() 求取流元素平均值。
         */
        IntStream intStream3 = Arrays.stream(numbers).mapToInt(Integer::intValue);
        OptionalDouble average = intStream2.average();

        /**
         * 5. 求和：sum() 对所有流元素进行求和。
         */
        IntStream intStream4 = Arrays.stream(numbers).mapToInt(Integer::intValue);
        int sum = intStream3.sum();
    }


    /**
     * 排序(sorted)
     */
    @Test
    public void testSorted01() {
        /**
         * 升序排列(重量)
         */
        System.out.println("========(升序排列)Lambda表达式=========================");
        appleList.stream()
                .sorted((a1, a2) -> Double.compare(a1.getWeight(), a2.getWeight()))
                .forEach(System.out::println);

        System.out.println("========(升序排列)方法引用=============================");
        appleList.stream()
                .sorted(Comparator.comparingDouble(Apple::getWeight))
                .forEach(System.out::println);
        /**
         * 降序排列(重量)
         */
        System.out.println("========(降序排列)Lambda表达式=========================");
        appleList.stream()
                .sorted((a1, a2) -> Double.compare(a2.getWeight(), a1.getWeight()))
                .forEach(System.out::println);

        System.out.println("========(降序排列)方法引用=========================");
        appleList.stream()
                .sorted(Comparator.comparingDouble(Apple::getWeight).reversed())
                .forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    public void testSorted02() {
        Integer[] numbers = {1, 3, 5, 2, 4, 6, 9};

        System.out.println("========默认顺序(自然顺序)=========================");
        Stream.of(numbers)
                .sorted()
                .forEach(System.out::println);

        System.out.println("========默认顺序(自然顺序反序)=========================");
        Stream.of(numbers)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

}
