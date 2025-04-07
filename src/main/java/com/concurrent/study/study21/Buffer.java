package com.concurrent.study.study21;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 王文涛
 * @date 2025/4/7
 * @description
 **/

@Data
@AllArgsConstructor
public class Buffer {

    private String name;

    private volatile Boolean expired;

    /**
     * 模拟清理缓存
     */
    public void clean() {

        if (expired) {

            System.out.println("缓冲区：" + name + "已过期");

            System.out.println("缓冲区：" + name + "开始清理");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("缓冲区：" + name + "清理完毕");

        } else {
            System.out.println("缓冲区：" + name + "没有过期");
        }
    }
}
