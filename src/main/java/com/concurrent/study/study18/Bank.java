package com.concurrent.study.study18;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王文涛
 * @date 2025/4/1
 * @description
 **/
public class Bank {

    private Bank() {
    }

    public static void transfer(Account from, Account to, double amount) {

        // 参数校验
        if (from == null || to == null) {
            throw new IllegalArgumentException("转账人或者被转账人不能为空");
        }

        if (from == to) {
            throw new IllegalArgumentException("不能给自己转钱");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("金额必须大于0");
        }

        // 固定顺序加锁（避免死锁）
        Account firstLock, secondLock;

        if (from.getId() < to.getId()) {
            firstLock = from;
            secondLock = to;
        } else {
            firstLock = to;
            secondLock = from;
        }

        // 加锁
        firstLock.getLock().lock();

        try {
            secondLock.getLock().lock();

            try {
                // 检查余额是否足够
                if (from.getBalance() < amount) {
                    throw new IllegalArgumentException("余额不足");
                }
                // 执行转账
                from.setBalance(from.getBalance() - amount);
                to.setBalance(to.getBalance() + amount);
            } finally {
                secondLock.getLock().unlock();
            }

        } finally {
            firstLock.getLock().unlock();
        }
    }

}
