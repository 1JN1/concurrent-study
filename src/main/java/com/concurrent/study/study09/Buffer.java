package com.concurrent.study.study09;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王文涛
 * @date 2025/3/12
 * @description
 **/
public class Buffer {

    private final LinkedList<Integer> queue = new LinkedList<>();

    private final int CAPACITY = 10;

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    public void producer(int item) throws InterruptedException {

        lock.lock();
        try {
            while (queue.size() >= CAPACITY) {
                System.out.println(Thread.currentThread().getName() + "等待：缓冲区已满");
                notFull.await();
            }


            Thread.sleep(1000);


            queue.add(item);
            System.out.println(Thread.currentThread().getName() + "生产：" + item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }

    }

    public void consumer() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() <= 0) {
                System.out.println(Thread.currentThread().getName() + "等待：缓冲区已空");
                notEmpty.await();
            }
            Thread.sleep(1000);
            Integer item = queue.poll();
            System.out.println(Thread.currentThread().getName() + "消费：" + item);
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

}
