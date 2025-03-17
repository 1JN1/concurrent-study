package com.concurrent.study.study13;

/**
 * @author 王文涛
 * @date 2025/3/17
 * @description
 **/
public class ReqThread implements Runnable {

    private final String userId;
    private final String instId;

    public ReqThread(String userId, String instId) {
        this.userId = userId;
        this.instId = instId;
    }

    @Override
    public void run() {

        ThreadLocalUtil.setUserId(userId);
        ThreadLocalUtil.setInstId(instId);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ThreadLocalUtil.getUserId();
        ThreadLocalUtil.getInstId();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String name = Thread.currentThread().getName();

        System.out.println(name + " userId: " + ThreadLocalUtil.getUserId() + " instId: " + ThreadLocalUtil.getInstId());

        System.out.println(name + " 执行完毕");

        ThreadLocalUtil.remove();
    }
}
