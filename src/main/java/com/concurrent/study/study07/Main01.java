package com.concurrent.study.study07;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 王文涛
 * @date 2025/3/8
 * @description
 **/
public class Main01 {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        // 固定频率执行，适合于任务执行时间比较固定的任务，任务执行时就开始计时，可能会导致任务堆叠
//        service.scheduleAtFixedRate(
//                () -> System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年M月dd日 HH:mm:ss"))),
//                0,
//                3,
//                TimeUnit.SECONDS
//        );

        // 固定延迟执行，适合于任务执行时间比较不稳定的任务，任务执行完成时开始计时，不会导致任务堆叠
        service.scheduleWithFixedDelay(
                () -> {
                    System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年M月dd日 HH:mm:ss")));
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                0,
                3,
                TimeUnit.SECONDS
        );
    }

}
