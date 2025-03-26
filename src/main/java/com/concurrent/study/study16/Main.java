package com.concurrent.study.study16;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 王文涛
 * @date 2025/3/26
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        Count count = new Count();

        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                20,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        List<Runnable> tasks = new ArrayList<>();
        // 10个线程去增加计数器
        for (int i = 0; i < 10; i++) {
            tasks.add(() -> {
                for (int j = 0; j < 1000; j++) {
                    count.add();
                }
            });
        }

        // 5个线程去读取计数器
        for (int i = 0; i < 5; i++) {
            tasks.add(() -> {
                System.out.println(count.getCount());
            });
        }

        tasks.forEach(executorService::execute);

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(count.getCount());
    }

}
