package com.concurrent.study.study01;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("非线程安全方式");
        System.out.println("==================================");

        for (int i = 0; i < 10; i++) {
            Sequence s = new Sequence("线程" + (i + 1));
            s.start();
        }

        Thread.sleep(2000);

        System.out.println("线程安全方式");
        System.out.println("==================================");
        ThreadSafeSequence safeSequence = new ThreadSafeSequence();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(safeSequence);
            t.start();
        }


    }

}
