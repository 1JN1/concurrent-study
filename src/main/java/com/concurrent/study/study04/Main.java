package com.concurrent.study.study04;

import java.util.concurrent.*;

/**
 * @author 王文涛
 * @date 2025/3/6
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            executorService.submit(
                    () -> {
                        try {
                            System.out.println(Thread.currentThread().getName() + "开始执行");
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println(Thread.currentThread().getName() + "执行结束");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }

        // 关闭线程池  不再接收任务，但是会执行现有任务
        executorService.shutdown();
        try {
            // 最多等待10s
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

    }

}
