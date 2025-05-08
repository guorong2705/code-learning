package com.guorong.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树实现
 */
class TrieTree {

    private TrieNode root;

    public TrieTree() {
        this.root = new TrieNode();
    }

    /**
     * 插入字符串
     *
     * @param world
     */
    public void insert(String world) {
        if (world == null || world.length() == 0) {
            throw new RuntimeException("world is empty");
        }
        // 转换为字符数组
        char[] charArray = world.toCharArray();
        TrieNode currTrieNode = root;
        currTrieNode.pass++;
        // 循环判断是否存在字符，不存在创建新字符
        for (char c : charArray) {
            Map<Character, TrieNode> map = currTrieNode.map;
            currTrieNode = map.get(c);
            if (currTrieNode == null) {
                currTrieNode = new TrieNode();
                map.put(c, currTrieNode);
            }
            currTrieNode.pass++;
        }
        currTrieNode.end++;
    }

    /**
     * 搜索完整单词
     *
     * @param world
     * @return
     */
    public boolean search(String world) {
        TrieNode node = getNode(world);
        // 判断是否存在结束标记
        return node != null && node.end > 0;
    }

    /**
     * 获取前缀个数
     *
     * @param prefix
     * @return 返回前缀出现的次数
     */
    public boolean searchPrefix(String prefix) {
        TrieNode node = getNode(prefix);
        return node != null;
    }

    /**
     * 移除字符单词
     *
     * @param world
     * @return
     */
    public boolean remove(String world) {
        return false;
    }

    private TrieNode getNode(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            throw new RuntimeException("world is empty");
        }
        TrieNode currTrieNode = root;
        char[] charArray = prefix.toCharArray();
        for (char c : charArray) {
            currTrieNode = currTrieNode.map.get(c);
            // 不存在路径返回
            if (currTrieNode == null) {
                break;
            }
        }
        return currTrieNode;
    }


    // 节点类
    private class TrieNode {
        private int pass;
        private int end;

        private Map<Character, TrieNode> map;

        public TrieNode() {
            this.pass = 0;
            this.end = 0;
            this.map = new HashMap<>();
        }
    }
}
