package com.ly.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 12:20 2018/10/12
 * @Modified By:
 */

@Slf4j
public class CountDownLatchExample {

    private final static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch cdl = new CountDownLatch(threadTotal);

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < threadTotal; i++) {

            final int threadNum = i;
            service.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                cdl.countDown();
            });
        }

        // 利用CountDownLatch的await()方法只能等待10ms，但是test()方法需要睡眠100ms在执行，所以会先输出"finish"。
        cdl.await(10, TimeUnit.MILLISECONDS);
//        cdl.await();
        log.info("finish");
        service.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("Thread name is {}", threadNum);
    }
}
