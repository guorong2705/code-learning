package com.guorong.tree;

import com.guorong.ArrayUtils;
import com.guorong.tree.impl.AVLTree;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 测试接口 IAVLTree
 */
public class IAVLTreeTests {

    /**
     * 测试插入
     */
    @Test
    public void testInsert() {
        AVLComparators.runInsertComparator(10000 * 100);
    }


    /**
     * 测试中序遍历
     */
    @Test
    public void testInOrder() {
        AVLComparators.runInOrderComparator(10000 * 100);
    }

    /**
     * 测试包含方法
     */
    @Test
    public void testContains() {
        AVLComparators.runContainsComparator(10000 * 10);
    }

    @Test
    public void testRemove() {
        AVLComparators.runRemoveComparator(10000 * 10);
    }


    /**
     * AVL树对数器
     */
    private static class AVLComparators {

        public static void runRemoveComparator(int testCaseCount) {
            int maxLen = 100;
            int maxValue = 20;
            for (int i = 0; i < testCaseCount; i++) {
                // 随机数组
                int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
                // 随机数生成
                int randomNum = ArrayUtils.generateRandInt(maxValue);
                int selection = ArrayUtils.generateRandInt(1);
                if (selection == 1 && array.length > 0) {
                    randomNum = array[ArrayUtils.generateRandInt(array.length - 1)];
                }
                //int[] array = {16, 9,5,17,12,8,16,19,0,18};
                //int randomNum = 12;
                // 插入AVL树
                IAVLTree<Integer> avlTree = new AVLTree<>();
                Arrays.stream(array).forEach(avlTree::insert);

                // 从AVL树中移除 randomNum
                boolean removeResult = avlTree.remove(randomNum);

                // 判断是否是平衡的
                if (!avlTree.isValidAVLTree()) {
                    System.out.println("测试错误：第" + (i + 1) + "次测试(移除元素)");
                    System.out.println("输入数据：" + Arrays.toString(array));
                    System.out.println("移除数据：" + randomNum);
                    System.out.println("移除结果：" + removeResult);
                    return;
                }
            }
        }

        /**
         * AVL 的contains方法比较器
         *
         * @param testCaseCount 运行测试用例次数
         */
        public static void runContainsComparator(int testCaseCount) {
            int maxLen = 100;
            int maxValue = 100;
            for (int i = 0; i < testCaseCount; i++) {
                // 生成随机数组
                int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
                // 随机生成一个数字num(num可能在随机数组中，也可能不在)
                int randomNum = ArrayUtils.generateRandInt(maxValue);
                int selection = ArrayUtils.generateRandInt(1);
                // 选择数组中存在的值
                if (selection == 1 && array.length > 0) {
                    randomNum = array[ArrayUtils.generateRandInt(array.length - 1)];
                }
                // 插入AVL树
                IAVLTree<Integer> avlTree = new AVLTree<>();
                Arrays.stream(array).forEach(avlTree::insert);
                // 参照组数据;
                TreeSet<Integer> treeSet = new TreeSet<>();
                Arrays.stream(array).forEach(treeSet::add);
                // 查找结果
                boolean avlResult = avlTree.contains(randomNum);
                boolean treeSetResult = treeSet.contains(randomNum);

                if (avlResult != treeSetResult) {
                    System.out.println("测试错误：第" + (i + 1) + "次测试(查找错误)");
                    System.out.println("输入数据：" + Arrays.toString(array));
                    System.out.println("随机数字：" + randomNum);
                    System.out.println("参考查找结果：" + treeSetResult);
                    System.out.println("AVL树查找结果：" + avlResult);
                    return;
                }
            }
        }


        /**
         * AVL 树中序遍历比较器
         *
         * @param testCaseCount 测试案例次数
         */
        public static void runInOrderComparator(int testCaseCount) {
            int maxLen = 100;
            int maxValue = 100;
            for (int i = 0; i < testCaseCount; i++) {
                // 生成随机数组
                int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);

                // 插入数据到 AVL树
                IAVLTree<Integer> avlTree = new AVLTree<>();
                Arrays.stream(array).forEach(avlTree::insert);

                // 参照组:TreeSet
                TreeSet<Integer> treeSet = new TreeSet<>();
                Arrays.stream(array).forEach(treeSet::add);

                // 获取中序遍历集合
                List<Integer> avlList = avlTree.inOrderTraversal();
                ArrayList<Integer> treeSetList = new ArrayList<>(treeSet);

                // 判断是否为升序
                if (!Objects.equals(avlList, treeSetList)) {
                    System.out.println("测试错误：第" + (i + 1) + "次测试(中序遍历)");
                    System.out.println("输入数据：" + Arrays.toString(array));
                    System.out.println("参照数据：" + treeSetList);
                    System.out.println("中序遍历数据：" + avlList);
                    return;
                }

            }
        }

        /**
         * 验证 AVL树插入比较器
         *
         * @param testCaseCount 测试案例次数
         */
        public static void runInsertComparator(int testCaseCount) {
            int maxLen = 100;
            int maxValue = 100;
            for (int i = 0; i < testCaseCount; i++) {
                // 生成长度和值都是随机的数组数据
                int[] data = ArrayUtils.generateRandomArray(maxLen, maxValue);
                //int[] data = {36, 60, 41, 72, 81, 43, 34, 50, 32, 35};

                // 插入AVL树
                IAVLTree<Integer> avlTree = new AVLTree<>();
                Arrays.stream(data).forEach(avlTree::insert);

                // 插入TreeSet参考树
                TreeSet<Integer> treeSet = new TreeSet<>();
                Arrays.stream(data).forEach(treeSet::add);

                // 中序遍历集合
                List<Integer> avlResultList = avlTree.inOrderTraversal();
                List<Integer> treeSetResultList = new ArrayList<>(treeSet);

                // 判断两个结果是否一样，不一样打印错误信息
                if (!treeSetResultList.equals(avlResultList)) {
                    System.out.println("测试错误：第" + (i + 1) + "次测试(中序遍历)");
                    System.out.println("输入数据：" + Arrays.toString(data));
                    System.out.println("AVL树结果：" + avlResultList);
                    System.out.println("TreeSet树参考结果：" + treeSetResultList);
                    return;
                }
                // 验证 AVL 树的平衡性和高度
                if (!avlTree.isValidAVLTree()) {
                    System.out.println("测试错误：第" + (i + 1) + "次测试(验证平衡性和高度错误)");
                    System.out.println("输入数据：" + Arrays.toString(data));
                    System.out.println("AVL树中序遍历结果：" + avlResultList);
                    return;
                }
            }
        }
    }
}
