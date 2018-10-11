package com.ly.concurrency.sync;

import com.ly.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 19:46 2018/10/11
 * @Modified By:
 */

@Slf4j
@ThreadSafe
public class SynchronizedExample1 {
    
    /*
    修饰代码块
     */
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                log.info("test1 -- {} -- {} ", j, i);
            }
        }
    }
    
    /*
    修饰方法
     */
    public synchronized void test2(int j) {
        for (int i = 0; i < 100; i++) {
            log.info("test2 -- {} -- {} ", j, i);
        }
    }
    
    public static void main(String[] args){

        SynchronizedExample1 example1 = new SynchronizedExample1();

        SynchronizedExample1 example2 = new SynchronizedExample1();


        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(()->{
            example1.test1(1);
        });

        service.execute(()->{
            example2.test1(2);
        });
    }
}
