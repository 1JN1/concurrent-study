package com.concurrent.study.study05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 王文涛
 * @date 2025/3/7
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Long> submit = executorService.submit(new MyCallable());
        executorService.submit(
                () -> 1
        );

        if (!submit.isDone()) {
            System.out.println("任务未完成");
        }

        try {
            Long result = submit.get();
            System.out.println("任务完成，结果为：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

}
