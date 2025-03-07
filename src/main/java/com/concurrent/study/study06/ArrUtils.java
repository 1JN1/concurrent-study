package com.concurrent.study.study06;

import java.util.Random;

/**
 * @author 王文涛
 * @date 2025/3/7
 * @description
 **/
public class ArrUtils {

    /**
     * 生成一个指定长度的随机数组，元素范围在 [0, bound) 之间
     *
     * @param length 数组长度
     * @param bound  随机数的上限（不包含）
     * @return 随机数组
     */
    public static int[] generateRandomArray(int length, int bound) {
        if (length <= 0 || bound <= 0) {
            throw new IllegalArgumentException("长度和上限必须大于 0");
        }

        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(bound);
        }
        return array;
    }

}
