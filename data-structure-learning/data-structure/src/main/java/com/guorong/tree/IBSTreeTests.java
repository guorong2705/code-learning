package com.guorong.tree;

import com.guorong.ArrayUtils;
import com.guorong.tree.impl.BSTree;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 二叉搜索树(BST)测试类
 */
public class IBSTreeTests {

    /**
     * 测试插入
     */
    @Test
    public void testInsert() {
        BSTCompartors.runInsertComparator(10000 * 100);
    }

    /**
     * 测试删除
     */
    @Test
    public void testRemove() {
        BSTCompartors.runRemoveComparator(10000 * 100);
    }

    /**
     * 测试中序遍历
     */
    @Test
    public void testInOrder() {
        BSTCompartors.runInOrderComparator(10000 * 100);
    }

    /**
     * 测试查找
     */
    @Test
    public void testContains() {
        BSTCompartors.runContainsComparator(10000 * 100);
    }


    /**
     * BST 树验证器
     */
    public static class BSTCompartors {

        /**
         * 插入测试对数器
         *
         * @param testCaseCount 测试案例次数
         */
        public static void runInsertComparator(int testCaseCount) {
            int maxLen = 100;
            int maxValue = 100;
            for (int i = 0; i < testCaseCount; i++) {
                // 生成随机数组
                int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
                //int[] array = {33, 18, 84, 20, 84};
                // 插入BST
                IBSTree<Integer> bsTree = new BSTree<>();
                Arrays.stream(array).forEach(bsTree::insert);
                // 验证BST树是否有效
                boolean isValid = bsTree.isValidBST();
                if (!isValid) {
                    System.out.println("测试失败：第" + (i + 1) + "次测试失败");
                    System.out.println("输入数据：" + Arrays.toString(array));
                    return;
                }
            }
        }

        /**
         * 移除功能对数器
         *
         * @param testCaseCount 测试案例次数
         */
        public static void runRemoveComparator(int testCaseCount) {
            int maxLen = 100;
            int maxValue = 100;
            for (int i = 0; i < testCaseCount; i++) {
                // 生成随机数组
                int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
                // 生成随机数，用于后续删除
                int randomNum = ArrayUtils.generateRandInt(maxValue);
                int selection = ArrayUtils.generateRandInt(1);
                if (selection == 1 && array.length > 0) {
                    randomNum = array[ArrayUtils.generateRandInt(array.length - 1)];
                }
                // 插入数据到BST
                IBSTree<Integer> bsTree = new BSTree<>();
                Arrays.stream(array).forEach(bsTree::insert);
                // 插入数据到参照TreeSet
                TreeSet<Integer> treeSet = new TreeSet<>();
                Arrays.stream(array).forEach(treeSet::add);
                // 执行删除，验证和参照物结果相同
                boolean bstResult = bsTree.remove(randomNum);
                boolean treeSetResult = treeSet.remove(randomNum);
                if (bstResult != treeSetResult) {
                    System.out.println("测试失败：第" + (i + 1) + "次测试");
                    System.out.println("输入数据：" + Arrays.toString(array));
                    System.out.println("参照结果：" + treeSetResult);
                    System.out.println("bst结果：" + bstResult);
                    return;
                }
                // 验证删除后，是否还符合BST规则
                if (!bsTree.isValidBST()) {
                    System.out.println("测试失败：第" + (i + 1) + "次测试");
                    System.out.println("输入数据：" + Arrays.toString(array));
                    return;
                }
            }
        }


        /**
         * 中序遍历对数器
         *
         * @param testCaseCount 案例测试次数
         */
        public static void runInOrderComparator(int testCaseCount) {
            int maxLen = 100;
            int maxValue = 100;
            for (int i = 0; i < testCaseCount; i++) {
                // 生成随机数组
                int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
                // 插入BST树
                IBSTree<Integer> ibsTree = new BSTree<>();
                Arrays.stream(array).forEach(ibsTree::insert);
                // 使用TreeSet作为参照结果
                TreeSet<Integer> treeSet = new TreeSet<>();
                Arrays.stream(array).forEach(treeSet::add);
                // 比较两个数是否相同
                List<Integer> bstList = ibsTree.inOrderTraversal();
                List<Integer> treeSetList = new ArrayList<>(treeSet);
                // 验证结果
                if (!Objects.equals(bstList, treeSetList)) {
                    System.out.println("测试失败：第" + (i + 1) + "次测试失败");
                    System.out.println("输入数据：" + Arrays.toString(array));
                    System.out.println("bst结果：" + bstList);
                    System.out.println("参照结果：" + treeSetList);
                    return;
                }
            }
        }


        /**
         * 测试查找功能
         *
         * @param testCaseCount 案例运行次数
         */
        public static void runContainsComparator(int testCaseCount) {
            int maxLen = 100;
            int maxValue = 100;
            for (int i = 0; i < testCaseCount; i++) {
                // 生成随机数组
                int[] array = ArrayUtils.generateRandomArray(maxLen, maxValue);
                // 生成查找值
                int random = ArrayUtils.generateRandInt(maxValue);
                int selection = ArrayUtils.generateRandInt(1);
                if (selection == 1 && array.length > 0) {
                    random = array[ArrayUtils.generateRandInt(array.length - 1)];
                }
                // 插入BST树
                IBSTree<Integer> bsTree = new BSTree<>();
                Arrays.stream(array).forEach(bsTree::insert);
                // 插入TreeSet参照树
                TreeSet<Integer> treeSet = new TreeSet<>();
                Arrays.stream(array).forEach(treeSet::add);
                // 判断两个查找结果
                boolean bstResult = bsTree.contains(random);
                boolean treeSetResult = treeSet.contains(random);
                if (bstResult != treeSetResult) {
                    System.out.println("测试失败，第" + (i + 1) + "次测试失败");
                    System.out.println("输入数据：" + Arrays.toString(array));
                    System.out.println("参照测试结果：" + treeSetResult);
                    System.out.println("BST测试结果：" + bstResult);
                    return;
                }
            }
        }
    }
}


















