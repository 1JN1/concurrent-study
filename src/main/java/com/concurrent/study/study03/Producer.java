package com.concurrent.study.study03;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description 生产者
 **/
public class Producer implements Runnable {

    /**
     * 当前线程已经生成的产品数目
     */
    private int num = 0;

    private Buffer<Product> buffer;

    public Producer(Buffer<Product> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        String name = Thread.currentThread().getName();

        while (true) {

            Product product = Product.product();
            System.out.println(name + "成产了产品：" + product.getName());

            buffer.put(product);
            System.out.println(name + "将产品放入缓冲区");

            System.out.println(name + "已经生产了" + ++num + "个产品");
        }

    }
}
