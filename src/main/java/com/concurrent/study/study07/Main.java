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
public class Main {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        // 一段时间后执行一个任务
        service.schedule(
                () -> System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年M月dd日 HH:mm:ss"))),
                3,
                TimeUnit.SECONDS
        );

        service.shutdown();
    }

}
