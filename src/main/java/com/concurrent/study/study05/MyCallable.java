package com.concurrent.study.study05;

import java.util.concurrent.Callable;

/**
 * @author 王文涛
 * @date 2025/3/7
 * @description
 **/
public class MyCallable implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        Thread.sleep(1000);
        return System.currentTimeMillis();
    }
}
