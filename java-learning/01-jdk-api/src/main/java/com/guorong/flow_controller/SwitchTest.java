package com.guorong.flow_controller;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * switch 流程控制
 * @author guorong
 */
public class SwitchTest {


    /**
     * 下面这个例子可随机生成字母，并判断它们是元音还是辅音字母
     */
    @Test
    public void test01() {
        // 自定义随机数产生因子: 47
        Random random = new Random(47);

        for (int i = 0; i < 20; i++) {
             // 随机生成英文字母
             int c = random.nextInt(26) + 'a';
             switch (c) {
                 case 'a':
                 case 'e':
                 case 'i':
                 case 'o':
                 case 'u':
                     System.out.println("元音字母 : " + (char)c);
                     break;
                 case 'y':
                 case 'w':
                     System.out.println("半元音字母 : " + (char)c);
                     break;
                 default:
                     System.out.println("辅音字母 : " + (char)c);
                     break;
             }
        }

    }


}
