package com.ly.concurrency.atomic;

import com.ly.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 19:00 2018/10/11
 * @Modified By: CAS的ABA问题，利用版本号解决
 */

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicBoolean isBoolean = new AtomicBoolean(false);

    // 请求总数
    private static int clientTotal = 5000;

    // 执行的线程数
    private static int threadTotal = 200;

    private static void test() {
        if (isBoolean.compareAndSet(false, true)) {
            log.info("execute");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Semaphore se = new Semaphore(threadTotal);

        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()-> {
                try {
                    se.acquire();
                    test();
                    se.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("isBoolean : {} ", isBoolean.get());
    }

}
