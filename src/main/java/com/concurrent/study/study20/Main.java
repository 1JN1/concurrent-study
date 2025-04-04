package com.concurrent.study.study20;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 王文涛
 * @date 2025/4/4
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        DataSource ds = new DataSource(10);

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 50; i++) {
            threadPool.submit(() -> {

                try {
                    ds.getConnection();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        threadPool.shutdown();

        // 等待5s，强制关闭线程池
        try {
            if (!threadPool.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                System.err.println("任务执行超时，强制关闭！");
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
