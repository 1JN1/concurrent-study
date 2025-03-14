package com.concurrent.study.study11;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 王文涛
 * @date 2025/3/14
 * @description
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        System.out.println("主线程分发线程任务....");

        long beginTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        System.out.println("主线程等待其他线程执行中....");
        countDownLatch.await();
        System.out.println("主线程执行结束");
        threadPool.shutdown();

        long endTime = System.currentTimeMillis();
        System.out.println("主线程执行耗时：" + (endTime - beginTime) + "ms");

    }

}
