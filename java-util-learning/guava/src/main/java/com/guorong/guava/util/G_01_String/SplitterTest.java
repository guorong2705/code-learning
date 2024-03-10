package com.guorong.guava.util.G_01_String;

import com.google.common.base.Splitter;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SplitterTest {


    @Test
    public void test01() {
        String s = "dg,dg,dg,ete";
        List<String> list = Splitter.on(",").splitToList(s);
        list.stream().forEach(System.out::println);
    }


}
