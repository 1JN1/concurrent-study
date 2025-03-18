package com.concurrent.study.study15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 王文涛
 * @date 2025/3/20
 * @description
 **/
public class SumFunction {

    public static long getSumSequence(long begin, long end) {

        long total = 0;

        for (long i = begin; i <= end; i++) {
            total += i;
        }

        return total;
    }

    public static long getSumParallel(long begin, long end) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        int n = Runtime.getRuntime().availableProcessors();
        long len = (end - begin) / n;

        List<Callable<Long>> tasks = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {

            long s = begin + i * len;
            long e = (i == n - 1) ? end : s + len - 1;

            Callable<Long> callable = new SumCallable(s, e);
            tasks.add(callable);
        }

        List<Future<Long>> futures = null;
        try {
            futures = threadPool.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        AtomicLong total = new AtomicLong();

        futures.forEach(future -> {
            try {
                total.addAndGet(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS)) {
                System.err.println("任务执行超时，强制关闭！");
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return total.get();
    }

}
