package com.concurrent.study.study02;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description
 **/
public class MyRunnable implements Runnable {
    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName());

    }
}
