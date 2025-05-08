package com.guorong.tree;

import org.junit.jupiter.api.Test;

// 前缀树测试
class TrieTreeTests {

    @Test
    public void testInsert() {
        String[] array = {"abc", "abfgd"};
        TrieTree trieTree = new TrieTree();
        for (int i = 0; i < array.length; i++) {
            trieTree.insert(array[i]);
        }
        System.out.println(trieTree.search("abcd"));
    }
}
