package com.concurrent.study.study08;

/**
 * @author 王文涛
 * @date 2025/3/11
 * @description
 **/
public class Main {

    public static void main(String[] args) {


        TestRunnable runnable = new TestRunnable();
        Thread thread1 = new Thread(runnable, "线程一");
        Thread thread2 = new Thread(runnable, "线程二");
        Thread thread3 = new Thread(runnable, "线程三");
        Thread thread4 = new Thread(runnable, "线程四");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

}
