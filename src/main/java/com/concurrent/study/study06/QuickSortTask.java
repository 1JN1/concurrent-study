package com.concurrent.study.study06;

import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @author 王文涛
 * @date 2025/3/7
 * @description
 **/
public class QuickSortTask implements Callable<int[]> {

    private final int[] arr;
    private final int left;
    private final int right;

    public QuickSortTask(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    public int[] call() throws Exception {
        Arrays.sort(arr, left, right + 1);
        return Arrays.copyOfRange(arr, left, right + 1);
    }
}
