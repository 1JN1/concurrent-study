package com.concurrent.study.study12;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author 王文涛
 * @date 2025/3/16
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获得许可");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放许可");
                    semaphore.release();
                }
            }).start();
        }

    }

}
