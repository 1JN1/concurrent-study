package com.concurrent.study.study10;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 王文涛
 * @date 2025/3/13
 * @description
 **/
public class Player implements Runnable {

    private CyclicBarrier barrier;

    private String name;

    public Player(CyclicBarrier barrier, String name) {
        this.barrier = barrier;
        this.name = name;
    }


    @Override
    public void run() {

        try {
            int matchTime = ThreadLocalRandom.current().nextInt(1000, 5000);
            System.out.println(name + " 开始匹配");
            Thread.sleep(matchTime);
            System.out.println(name + " 等待其他玩家匹配中...");
            barrier.await();

            System.out.println(name + " 开始游戏");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
