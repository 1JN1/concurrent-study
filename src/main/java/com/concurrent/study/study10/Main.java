package com.concurrent.study.study10;

import java.util.concurrent.CyclicBarrier;

/**
 * @author 王文涛
 * @date 2025/3/13
 * @description
 **/
public class Main {

    public static void main(String[] args) {

        int totalPlayers = 10;

        CyclicBarrier barrier = new CyclicBarrier(
                totalPlayers, () -> System.out.println("所有玩家匹配成功，正在准备进入游戏...")
        );

        for (int i = 0; i < totalPlayers; i++) {
            new Thread(new Player(barrier, "玩家" + (i + 1))).start();
        }

    }

}
