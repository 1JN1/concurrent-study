package com.concurrent.study.study03;


/**
 * @author 王文涛
 * @date 2025/3/5
 * @description 缓冲区
 **/
public class Buffer<T> {

    /**
     * 默认缓冲区大小
     */
    private final int DEFAULT_CAPACITY = 10;

    /**
     * 缓冲区中的物品数量
     */
    private int size = 0;

    /**
     * 缓冲区中的物品
     */
    private final Object[] items;

    public Buffer() {
        this.items = new Object[DEFAULT_CAPACITY];
    }

    public Buffer(int capacity) {
        this.items = new Object[capacity];
    }

    public synchronized void put(T item) {

        while (size >= items.length) {
            try {
                System.out.println("缓冲区已满，等待中...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        items[size++] = item;
        System.out.println("物品被放入缓冲区，当前缓冲区容量为：" + size);
        notifyAll();
    }

    @SuppressWarnings("unchecked")
    public synchronized T get() {

        T item = null;

        while (size == 0) {

            try {
                System.out.println("缓冲区已空，等待中...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        item = (T) items[--size];
        System.out.println("物品被取出缓冲区，当前缓冲区容量为：" + size);
        notifyAll();

        return item;
    }

}
