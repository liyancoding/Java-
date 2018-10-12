package com.ly.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 12:20 2018/10/12
 * @Modified By:
 */

@Slf4j
public class CyclicBarrierExample {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < 15; i++) {
            final int threadNum = i;
            Thread.sleep(100);

            es.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }

        es.shutdown();
    }

    private static void test(int threadNum) throws Exception {

        Thread.sleep(100);
        log.info("{} is ready", threadNum);

        // 由于循环屏障参数为5，表示屏障拦截线程的数量，所以当计数达到5时阻塞线程，调用await()方法告诉
        // CyclicBarrier已经达到了屏障
        cyclicBarrier.await();

        // await()方法可以设置计时功能
//        try {
//            cyclicBarrier.await(200, TimeUnit.MILLISECONDS);
//        } catch (Exception e) {
//            log.error("BarrierException", e);
//        }
        log.info("{} continue", threadNum);

    }
}
