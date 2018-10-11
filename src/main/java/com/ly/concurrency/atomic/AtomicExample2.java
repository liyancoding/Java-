package com.ly.concurrency.atomic;

import com.ly.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 18:15 2018/10/11
 * @Modified By:
 */

@Slf4j
@ThreadSafe
public class AtomicExample2 {

    // 请求的总数
    public static int clientTotal = 5000;

    // 同步执行的线程数
    public static int threadTotal = 200;

    // 计数
    public static LongAdder count = new LongAdder();

    public static void add() {
        count.increment();
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
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        es.shutdown();
        log.info("count:{}",count);
    }
}
