package com.guorong.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bcd");
        list.add("bbb");
        list.add("ace");
        list.add("snb");
        list.add("aaaa");
        list.add("bbbbb");
        list.add("eeee");
        List<String>[] lists = groupString(list);
    }

    public static String vowelOnly(String input) {
        String regex = "aeiou";
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (!regex.contains(String.valueOf(c).toLowerCase())) {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static List<String>[] groupString(List<String> input) {
        List<String> aList = new ArrayList<>();
        List<String> bList = new ArrayList<>();
        List<String> otherList = new ArrayList<>();
        for (String element : input) {
            if (element.startsWith("a")) {
                aList.add(element);
            } else if (element.startsWith("b")) {
                bList.add(element);
            } else {
                otherList.add(element);
            }
        }
        return (List<String>[]) Arrays.asList(aList, bList, otherList).toArray();
    }




}
