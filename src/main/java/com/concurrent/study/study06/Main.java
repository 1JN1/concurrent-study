package com.concurrent.study.study06;

import java.util.Arrays;
import java.util.Random;

/**
 * @author 王文涛
 * @date 2025/3/7
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        double total1 = 0, total2 = 0;

        for (int i = 0; i < 10; i++) {
            int[] ints = ArrUtils.generateRandomArray(100000000, 100);

            // 多线程快排执行时间
            long start = System.currentTimeMillis();
            ints = Sort.parallelQuickSort(ints);
            long end = System.currentTimeMillis();
            total1 += end - start;

        }

        System.out.println("多线程快排执行时间：" + (total1 / 10) + "ms");

        for (int i = 0; i < 10; i++) {
            int[] temp = ArrUtils.generateRandomArray(100000000, 100);
            // 普通快排执行时间
            long start = System.currentTimeMillis();
            Arrays.sort(temp);
            long end = System.currentTimeMillis();
            total2 += end - start;
        }
        System.out.println("普通快排执行时间：" + (total2 / 10) + "ms");
    }

}
