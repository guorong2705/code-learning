package com.guorong.hutool.util.str;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author guorong
 * @date 2021-05-24
 */
public class StrUtilTest {

    /**
     * 给定一些字符串，如果一旦有空的就返回true，常用于判断好多字段是否有空的（例如web表单数据）。
     * 这两个方法的区别是hasEmpty只判断是否为null或者空字符串（""），
     * hasBlank则会把不可见字符也算做空，isEmpty和isBlank同理。
     */
    @Test
    public void hasBlankAndHasEmpty() {
        System.out.println("是否包含空字符串: " + StrUtil.hasEmpty("dfdf", ""));
        System.out.println("是否包含空字符串: " + StrUtil.hasEmpty("dfdf", " "));
        System.out.println("==============================================");
        System.out.println("是否包含空字符串: " + StrUtil.hasBlank("dfdf", ""));
        System.out.println("是否包含空字符串: " + StrUtil.hasBlank("dfdf", " "));
    }


    /**
     * 去掉字符串的后缀，例如去个文件名的扩展名。
     */
    @Test
    public void removeSuffix() {
        // 去掉后缀
        String fileName = "country.txt";
        System.out.println("去掉后缀：" + StrUtil.removeSuffix(fileName, ".txt"));
        System.out.println("去掉后缀：" + StrUtil.removeSuffix(fileName, ".TXT"));
        // 去掉后缀忽略大小写
        System.out.println("去掉后缀(忽略大小写)：" + StrUtil.removeSuffixIgnoreCase(fileName, ".TXT"));
    }


    /**
     * 截取字符串:
     * 不得不提一下这个方法，有人说String有了subString你还写它干啥，我想说subString方法越界啥的都会报异常，
     * 你还得自己判断，难受死了，我把各种情况判断都加进来了，而且index的位置还支持负数，
     * -1表示最后一个字符（这个思想来自于Python，如果学过Python的应该会很喜欢的），
     * 还有就是如果不小心把第一个位置和第二个位置搞反了，也会自动修正（例如想截取第4个和第2个字符之间的部分也是可以的）
     */
    @Test
    public void sub() {
        String str = "abcdefgh";
        // strSub1 -> c
        System.out.println(StrUtil.sub(str, 2, 3));
        // strSub2 -> cde
        System.out.println(StrUtil.sub(str, 2, -3));
        // strSub2 -> c
        System.out.println(StrUtil.sub(str, 3, 2));
    }


    /**
     * 切分字符串，去除切分后每个元素两边的空白符，去除空白项
     */
    @Test
    public void testSplitTrim() {
        String line = "张三,";
        List<String> split = StrUtil.splitTrim(line, ",");
        for (String s : split) {
            System.out.println(s + "--");
        }
    }




}
