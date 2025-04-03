package com.concurrent.study.study19;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpHead;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpStatus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 王文涛
 * @date 2025/4/3
 * @description
 **/

public class Download {

    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    public static void download(String url, String fileName) throws IOException {

        if (Objects.isNull(url) || url.isBlank()) {
            throw new RuntimeException("url is null");
        }
        if (Objects.isNull(fileName) || fileName.isBlank()) {
            throw new RuntimeException("fileName is null");
        }

        // 获取文件大小
        long fileSize = getFileSize(url);

        // 获取要启用的线程数
        int threadNum = Runtime.getRuntime().availableProcessors();

        // 每块大小
        long blockSize = fileSize / threadNum;

        // 创建临时文件数组
        Path[] tempFiles = new Path[threadNum];
        for (int i = 0; i < threadNum; i++) {
            tempFiles[i] = Files.createTempFile("download-temp-", ".tmp");
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(threadNum);
        CountDownLatch latch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {

            final int index = i;
            final long start = index * blockSize;
            final long end = index == threadNum - 1 ? fileSize : (index + 1) * blockSize;

            threadPool.execute(() -> {

                try {
                    downloadChunk(url, start, end, tempFiles[index]);
                    latch.countDown();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 合并文件
        merge(tempFiles, fileName);

        // 删除临时文件
        deleteTempFiles(tempFiles);

        threadPool.shutdown();
    }

    private static long getFileSize(String url) throws IOException {

        AtomicLong fileSize = new AtomicLong(0);

        HttpHead httpHead = new HttpHead(url);

        HTTP_CLIENT.execute(httpHead, response -> {
            System.out.println(response);
            if (response.getCode() == HttpStatus.SC_SUCCESS) {
                fileSize.set(
                        Long.parseLong(
                                response.getFirstHeader(HttpHeaders.CONTENT_LENGTH).getValue()
                        )
                );

            }

            response.close();
            return null;
        });

        return fileSize.get();
    }

    private static void downloadChunk(String url, long start, long end, Path path) throws IOException {

        HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader(HttpHeaders.RANGE, "bytes=" + start + "-" + end);

        HTTP_CLIENT.execute(httpGet, response -> {

            if (response.getCode() == HttpStatus.SC_PARTIAL_CONTENT) {

                HttpEntity entity = response.getEntity();

                try (InputStream is = entity.getContent()) {

                    Files.write(path, is.readAllBytes());

                }

            }

            response.close();
            return null;
        });

    }

    private static void merge(Path[] tempFiles, String fileName) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (Path tempFile : tempFiles) {

                try (InputStream is = Files.newInputStream(tempFile)) {

                    writer.write(new String(is.readAllBytes()));

                }

            }

        }

    }

    private static void deleteTempFiles(Path[] tempFiles) throws IOException {

        for (Path tempFile : tempFiles) {

            Files.delete(tempFile);

        }

    }

}
