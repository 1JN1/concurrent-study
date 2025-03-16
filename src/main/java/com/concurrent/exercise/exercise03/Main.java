package com.concurrent.exercise.exercise03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 王文涛
 * @date 2025/3/16
 * @description
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<Product> products = List.of(
                new Product(1L, "电视"),
                new Product(2L, "冰箱"),
                new Product(3L, "洗衣机")
        );

        List<String> result = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(3);

        try {
            // 使用 invokeAll 批量提交任务，并设置 5 秒超时
            List<Future<String>> futures = service.invokeAll(
                    products.stream()
                            .map(product -> (Callable<String>) () -> Product.process(product))
                            .toList(),
                    5, TimeUnit.SECONDS
            );

            // 处理任务结果
            for (Future<String> future : futures) {
                try {
                    result.add(future.get()); // 任务成功完成
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    result.add("任务被中断");
                } catch (ExecutionException e) {
                    result.add("任务执行异常：" + e.getCause().getMessage());
                } catch (CancellationException e) {
                    result.add("任务超时，被取消");
                }
            }

        } finally {
            service.shutdown();
            if (!service.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("任务执行超时，强制关闭！");
                service.shutdownNow();
            }
        }

        // 输出结果
        for (String s : result) {
            System.out.println(s);
        }
    }
}
