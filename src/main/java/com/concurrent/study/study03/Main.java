package com.concurrent.study.study03;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description
 **/
public class Main {

    public static void main(String[] args) {


        Buffer<Product> buffer = new Buffer<>();


        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(buffer), "生产者" + (i + 1)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(buffer), "消费者" + (i + 1)).start();
        }

    }

}
