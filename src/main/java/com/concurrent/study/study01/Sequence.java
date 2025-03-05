package com.concurrent.study.study01;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 王文涛
 * @date 2025/3/5
 * @description
 **/
public class Sequence extends Thread {

    private static int value = 0;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

    public int getValue() {

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return value++;
    }

    public Sequence(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int v = getValue();
            // `add` 返回 false 说明已经存在，表示重复！
            if (!set.add(v)) {
                System.out.printf("⚠️ 线程 %s 发现重复值：%d\n", Thread.currentThread().getName(), v);
            }

            System.out.println("线程 " + Thread.currentThread().getName() + " 结束");
        }
    }
}
