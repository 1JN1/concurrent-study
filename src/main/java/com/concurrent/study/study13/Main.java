package com.concurrent.study.study13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 王文涛
 * @date 2025/3/17
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 20; i++) {
            executorService.submit(
                    new ReqThread("userId" + (i + 1), "instId" + (i + 1))
            );
        }

        executorService.shutdown();

    }

}
