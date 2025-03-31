package com.concurrent.study.study17;

import com.concurrent.study.study03.Product;

/**
 * @author 王文涛
 * @date 2025/3/31
 * @description
 **/
public class Producer implements Runnable {

    private final Buffer<Product> buffer;

    private final String name;

    private final static String PRODUCER_NAME = "producer";

    public Producer(Buffer<Product> buffer) {
        this(buffer, PRODUCER_NAME);
    }

    public Producer(Buffer<Product> buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    private void produce(Product t) throws InterruptedException {
        System.out.println(name + "正在准备将" + t + "放入缓冲区");
        buffer.put(t);
        System.out.println(name + "将" + t + "放入缓冲区");
    }

    @Override
    public void run() {

        while (true) {

            Product product = Product.product();
            System.out.println(name + "成产了产品：" + product.getName());
            try {
                produce(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
