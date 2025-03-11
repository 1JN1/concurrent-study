package com.concurrent.study.study08;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王文涛
 * @date 2025/3/11
 * @description
 **/
public class TestRunnable implements Runnable {

    private int count = 0;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        String name = Thread.currentThread().getName();
        for (int i = 0; i < 100; i++) {

            lock.lock();
            try {
                Thread.sleep(500);
                System.out.println(name + "正在运行，当前count为：" + count++);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

    }
}
