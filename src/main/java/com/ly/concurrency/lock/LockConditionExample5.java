package com.ly.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockConditionExample5 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        // ReentrantLock利用Condition绑定多个条件
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();

                log.info("wait signal"); // 1

                // 等待，调用await()方法，当前线程进入等待队列并且释放锁
                condition.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("get signal"); // 4

            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 通知，调用signal()/signalAll()方法，将会唤醒在等待队列中等待的线程
            condition.signal();
//            condition.signalAll();

            log.info("send signal ~ "); // 3

            reentrantLock.unlock();

        }).start();
    }
}
