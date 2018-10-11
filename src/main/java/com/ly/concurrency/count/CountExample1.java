package com.ly.concurrency.count;

import com.ly.concurrency.annoations.NotThreadSafe;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 18:15 2018/10/11
 * @Modified By:
 */

@Slf4j
@NotThreadSafe
public class CountExample1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountExample1.class);

    // 请求的总数
    public static int clientTotal = 5000;

    // 同步执行的线程数
    public static int threadTotal = 200;

    // 计数
    public static int count = 0;

    public static void add() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            es.execute(()->
            {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    LOGGER.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        es.shutdown();
        LOGGER.info("count:{}",count);
    }
}
