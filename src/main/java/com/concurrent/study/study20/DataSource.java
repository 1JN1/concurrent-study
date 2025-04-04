package com.concurrent.study.study20;

import java.util.concurrent.Semaphore;

/**
 * @author 王文涛
 * @date 2025/4/4
 * @description 模拟数据库连接池
 **/
public class DataSource {

    private final Semaphore semaphore;

    public DataSource(int max) {
        semaphore = new Semaphore(max);
    }

    public void getConnection() throws InterruptedException {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() + "等待数据库连接");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "获取数据库连接");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "释放数据库连接");
        semaphore.release();
    }

}
