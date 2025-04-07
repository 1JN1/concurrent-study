package com.concurrent.study.study21;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author 王文涛
 * @date 2025/4/7
 * @description
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<Buffer> buffers = Arrays.asList(
                new Buffer("buffer1", false),
                new Buffer("buffer2", false),
                new Buffer("buffer3", false),
                new Buffer("buffer4", false),
                new Buffer("buffer5", false)
        );

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
                1,
                r -> new Thread(r, "定时器 - " + System.currentTimeMillis())
        );

        executor.scheduleWithFixedDelay(
                () -> buffers.forEach(Buffer::clean),
                1,
                3,
                TimeUnit.SECONDS
        );

        Thread.sleep(5000);

        int randIdx = ThreadLocalRandom.current().nextInt(buffers.size());
        buffers.get(randIdx).setExpired(true);


        Thread.sleep(5000);

        executor.shutdown();


        if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }

    }

}
