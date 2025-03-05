package com.concurrent.study.study03;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description 消费者
 **/
public class Consumer implements Runnable {

    private int num = 0;

    private Buffer<Product> buffer;

    public Consumer(Buffer<Product> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        String name = Thread.currentThread().getName();

        while (true) {

            Product product = buffer.get();
            System.out.println(name + "从缓冲区获取到产品：" + product.getName());

            product.use();

            System.out.println(name + "已经消费了" + ++num + "个产品");
        }

    }
}
