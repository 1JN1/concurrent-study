package com.concurrent.exercise.exercise02;

/**
 * @author 王文涛
 * @date 2025/3/13
 * @description
 **/
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
