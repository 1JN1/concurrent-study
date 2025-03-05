package com.concurrent.study.study02;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description
 **/
public class Test {

    public static void main(String[] args) {

        Thread thread = new Thread(new MyRunnable(), "测试线程");
        thread.start();

    }

}
