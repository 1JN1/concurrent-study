package com.concurrent.study.study18;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王文涛
 * @date 2025/4/1
 * @description
 **/

public class Account {

    @Getter
    private Long id;

    @Getter
    @Setter
    private Double balance;

    @Getter
    private final ReentrantLock lock = new ReentrantLock();

    public Account(Long id, Double balance) {
        this.id = id;
        this.balance = balance;
    }
}
