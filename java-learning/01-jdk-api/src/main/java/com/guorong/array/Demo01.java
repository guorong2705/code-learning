package com.guorong.array;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 简单抽奖
 */
public class Demo01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要抽奖的最大值");
        int n = scanner.nextInt();

        System.out.println("请输入中奖的个数");
        int count = scanner.nextInt();

        // 初始化号码容器
        int[] numberArray = new int[n];
        for (int i = 0; i < n; i++) {
            numberArray[i] = i + 1;
        }

        Random random = new Random();

        // 获奖号码
        int[] resultArray = new int[count];
        for (int i = 0; i < resultArray.length; i++) {
            int randIndex = random.nextInt(n);
            resultArray[i] = numberArray[randIndex];
            // 移动最后一个元素到随机数的位置
            numberArray[randIndex] = numberArray[n-1];
            // 将最后一个位置排除随机数生成的范围
            n--;
        }

        // 对数据进行排序
        Arrays.sort(resultArray);
        System.out.println(Arrays.toString(resultArray));

    }
}
