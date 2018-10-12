package com.ly.concurrency.lock;

import com.ly.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 13:04 2018/10/12
 * @Modified By:
 */

@Slf4j
@ThreadSafe
public class LockExample1 {

    // 并发执行的线程数
    private static int threadTotal = 200;

    // 请求总数
    private static int clientTotal = 5000;

    private static int count = 0;

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(threadTotal);

        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    sem.acquire();
                    add();
                    sem.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("count = {}", count);
    }

    private static void add() {
//        count++; // 不加锁

        // 加锁
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }
    }
}
