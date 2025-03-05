package com.concurrent.exercise.exercise01;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description
 **/
public class HelloWorldThread extends Thread{

    @Override
    public void run() {
        System.out.println("hello world");
    }
}
