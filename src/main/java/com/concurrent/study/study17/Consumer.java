package com.concurrent.study.study17;

import com.concurrent.study.study03.Product;

/**
 * @author 王文涛
 * @date 2025/3/31
 * @description
 **/
public class Consumer implements Runnable {

    private final String name;

    private final Buffer<Product> buffer;

    private final static String CONSUMER_NAME = "consumer";

    public Consumer(Buffer<Product> buffer) {
        this(buffer, CONSUMER_NAME);
    }

    public Consumer(Buffer<Product> buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    private void consume() throws InterruptedException {

        Product product = buffer.take();
        System.out.println(name + "从缓冲区获取到产品：" + product.getName());

        product.use();
        System.out.println(name + "消费掉了产品：" + product.getName());
    }

    @Override
    public void run() {

        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
