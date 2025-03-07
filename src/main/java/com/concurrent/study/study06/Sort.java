package com.concurrent.study.study06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 王文涛
 * @date 2025/3/7
 * @description
 **/
public class Sort {

    private static ExecutorService executor;

    public static int[] parallelQuickSort(int[] arr) {

        int fragNum = Math.min(Runtime.getRuntime().availableProcessors(), arr.length / 2);

        executor = Executors.newFixedThreadPool(fragNum);

        int len = arr.length / fragNum;

        Future<int[]>[] futures = new Future[fragNum];

        for (int i = 0; i < fragNum; i++) {

            int left = i * len;
            int right = (i == fragNum - 1) ? arr.length - 1 : left + len - 1;

            futures[i] = executor.submit(new QuickSortTask(arr, left, right));

        }

        // 收集排序后的片段
        int[][] result = new int[fragNum][];
        for (int i = 0; i < fragNum; i++) {
            try {
                result[i] = futures[i].get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        executor.shutdown();

        // 合并所有片段
        return mergeAll(result);
    }

    private static int[] mergeAll(int[][] arr) {

        if (arr.length == 0) return new int[0];
        if (arr.length == 1) return arr[0];

        // 递归合并
        int[][] newArrays = new int[(arr.length + 1) / 2][];
        for (int i = 0; i < arr.length; i += 2) {
            if (i + 1 < arr.length) {
                newArrays[i / 2] = merge(arr[i], arr[i + 1]);
            } else {
                newArrays[i / 2] = arr[i];
            }
        }
        return mergeAll(newArrays);
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] result = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            if (arr1[i] < arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < len1) {
            result[k++] = arr1[i++];
        }

        while (j < len2) {
            result[k++] = arr2[j++];
        }

        return result;
    }

}
