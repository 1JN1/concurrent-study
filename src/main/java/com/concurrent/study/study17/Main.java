package com.concurrent.study.study17;

import com.concurrent.study.study03.Product;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 王文涛
 * @date 2025/3/31
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(
                10,
                20,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        Buffer<Product> buffer = new Buffer<>(10);

        // 一个生产者
        threadPool.execute(new Producer(buffer));

        // 十个消费者
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Consumer(buffer, "消费者" + (i + 1)));
        }

        threadPool.shutdown();
        while (!threadPool.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
