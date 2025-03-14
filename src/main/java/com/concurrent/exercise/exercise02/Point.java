package com.concurrent.exercise.exercise02;

import lombok.Data;

/**
 * @author 王文涛
 * @date 2025/3/14
 * @description
 **/

@Data
public class Point {

    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
