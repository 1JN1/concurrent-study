package com.concurrent.study.study14;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 王文涛
 * @date 2025/3/18
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        AtomicInteger num = new AtomicInteger(0);
        int num1 = 0;

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.submit(new IncrThread(num, num1));

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        threadPool.submit(new DecrThread(num, num1));

        threadPool.shutdown();

    }

}
