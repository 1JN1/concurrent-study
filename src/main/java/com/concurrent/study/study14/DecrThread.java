package com.concurrent.study.study14;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 王文涛
 * @date 2025/3/18
 * @description
 **/
public class DecrThread implements Runnable {

    AtomicInteger num;
    int num1;

    public DecrThread(AtomicInteger num, int num1) {
        System.out.println("aaa" + num1);
        System.out.println("bbb" + num);
        this.num = num;
        this.num1 = num1;
    }

    @Override
    public void run() {

        String name = Thread.currentThread().getName();

        for (int i = 0; i < 50; i++) {

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " - num: " + num.decrementAndGet());
            System.out.println(name + " - num1: " + --num1);

        }

    }
}
