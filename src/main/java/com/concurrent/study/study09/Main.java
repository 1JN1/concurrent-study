package com.concurrent.study.study09;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 王文涛
 * @date 2025/3/12
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        Buffer buffer = new Buffer();

        // 生产者任务
        Runnable producerTask = () -> {
            try {
                for (int j = 0; j < 50; j++) {
                    buffer.producer(j);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1500)); // 随机等待
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // 消费者任务
        Runnable consumerTask = () -> {
            try {
                for (int j = 0; j < 50; j++) {
                    buffer.consumer();
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1500)); // 随机等待
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // 启动生产者和消费者线程
        for (int i = 0; i < 5; i++) {
            threadPool.execute(producerTask);
            threadPool.execute(consumerTask);
        }

        threadPool.shutdown();

    }

}
