package com.guorong.guava.util.G_01_String;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 字符串连接器
 * @author guorong
 */
public class JoinerTest {


    /**
     * 不包含null的字符串
     */
    private static final List<String> strList = Lists.newArrayList();

    static {
        strList.add("JAVA");
        strList.add("C++");
        strList.add("Python");
        strList.add("HTML");
        strList.add("javaScript");
    }


    /**
     * 包含 null的字符串
     */
    private static final List<String> stringWithNullList = Lists.newArrayList();
    static {
        stringWithNullList.add("JAVA");
        stringWithNullList.add("C++");
        stringWithNullList.add("Python");
        stringWithNullList.add(null);
        stringWithNullList.add("HTML");
    }

    /**
     * 字符串map集合
     */
    private static final Map<String,String> strMap = Maps.newHashMap();
    static {
        strMap.put("JAVA", "001");
        strMap.put("Python", "001");
        strMap.put("PHP", "001");
    }


    /**
     * 拼接字符串 元素中没有 null
     */
    @Test
    public void testOnJoinNonNullStr() {
        String str = Joiner.on("#").join(strList);
        String expectedStr = "JAVA#C++#Python#HTML#javaScript";
        Assert.assertEquals(expectedStr, str);
    }

    /**
     * 使用 JDK 8 中的Stream 实现拼接字符串
     */
    @Test
    public void testJoinWithJdk8() {
        String str = strList.stream()
                .collect(Collectors.joining("#"));
        String expectedStr = "JAVA#C++#Python#HTML#javaScript";
        Assert.assertEquals(expectedStr, str);

    }



    /**
     * 当拼接的字符串元素 存在 null 的时会出现 空指针异常
     */
    // @Test(expected = NullPointerException.class)
    @Test
    public void test01() {
        String str = Joiner.on("#").join(stringWithNullList);
        String expectedStr = "JAVA#C++#Python#HTML";
        Assert.assertEquals(expectedStr, str);
    }

    /**
     * 当元素中存在 null 的时，可以使用 skipNulls() 方法跳过元素为 NULL，就不会出现空指针异常
     */
    @Test
    public void testOnJoinSkipNull() {
        String str = Joiner.on("#").skipNulls().join(stringWithNullList);
        String expectedStr = "JAVA#C++#Python#HTML";
        Assert.assertEquals(expectedStr, str);
    }


    /**
     * 使用 JDK8 的 Stream 实现，拼接字符串元素的时候，跳过 null
     */
    @Test
    public void testSkipNullOnJdk8() {
        String str = stringWithNullList.stream()
                .filter(s -> Objects.nonNull(s))
                .collect(Collectors.joining("#"));

        String expectedStr = "JAVA#C++#Python#HTML";
        Assert.assertEquals(expectedStr, str);
    }


    /**
     * 当元素中存在 null 的时，可以使用 useForNull() 方法设置替代 null 的值
     */
    @Test
    public void test03() {
        String str = Joiner.on("#").useForNull("DEFAULT").join(stringWithNullList);
        String expectedStr = "JAVA#C++#Python#HTML#DEFAULT";
        Assert.assertEquals(expectedStr, str);
    }


    /**
     * 使用 jdk 8 中的 Stream 让，替换掉元素中的 null 为 DEFAULT
     */
    @Test
    public void test03OnJdk8() {
        String str = stringWithNullList.stream()
                .map(s -> Objects.isNull(s) ? "DEFAULT" : s)
                .collect(Collectors.joining("#"));

        String expectedStr = "JAVA#C++#Python#HTML#DEFAULT";
        Assert.assertEquals(expectedStr, str);
    }


    /**
     * 拼接 Map结合的 key 的值
     */
    @Test
    public void testOnJoinMap() {
        String str = Joiner.on("#").withKeyValueSeparator("=").join(strMap);
        String expectedStr = "JAVA=001#PHP=001#Python=001";
        Assert.assertEquals(expectedStr, str);
    }






}
