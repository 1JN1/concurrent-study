package com.concurrent.study.study16;

/**
 * @author 王文涛
 * @date 2025/3/26
 * @description
 **/
public class Count {

    private int count = 0;

    public void add() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        synchronized (this) {
            return count;
        }
    }
}
