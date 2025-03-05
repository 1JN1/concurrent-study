package com.concurrent.exercise.exercise01;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new HelloWorldThread();
        Thread thread1 = new HelloWorldThread();
        Thread thread2 = new HelloWorldThread();

        thread.start();
        thread1.start();
        thread2.start();


        thread.join();
        thread1.join();
        thread2.join();

        System.out.println("结束");

    }

}
