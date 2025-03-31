package com.concurrent.study.study17;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author 王文涛
 * @date 2025/3/31
 * @description 缓冲区
 **/
public class Buffer<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private final int capacity;

    private BlockingQueue<T> queue;

    public Buffer() {
        this(DEFAULT_CAPACITY);
    }


    public Buffer(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("容量必须大于0");
        }

        this.capacity = capacity;
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    /**
     * 添加元素
     * @param t
     * @throws InterruptedException
     */
    public void put(T t) throws InterruptedException {
        queue.put(t);
    }

    /**
     * 获取元素
     * @return
     * @throws InterruptedException
     */
    public T take() throws InterruptedException {
        return queue.take();
    }
}
