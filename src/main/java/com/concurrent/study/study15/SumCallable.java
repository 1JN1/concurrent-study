package com.concurrent.study.study15;

import java.util.concurrent.Callable;

/**
 * @author 王文涛
 * @date 2025/3/20
 * @description
 **/
public class SumCallable implements Callable<Long> {

    private final long begin;
    private final long end;
    private long sum;

    public SumCallable(long begin, long end) {
        this.begin = begin;
        this.end = end;
        this.sum = 0;
    }


    @Override
    public Long call() throws Exception {

        for (long i = begin; i <= end; i++) {
            sum += i;
        }


        // System.out.println(Thread.currentThread().getName() + " - (" + "begin - " + "end" + ") - sum: " + sum);

        return sum;
    }
}
