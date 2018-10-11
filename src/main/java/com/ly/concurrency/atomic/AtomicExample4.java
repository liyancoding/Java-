package com.ly.concurrency.atomic;

import com.ly.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 19:00 2018/10/11
 * @Modified By:
 */

@Slf4j
@ThreadSafe
public class AtomicExample4 {

    // 由于是AtomicIntegerFieldUpdater修饰，所以字段count必须声明为volatile，且不能用static修饰
    @Getter
    public volatile int count = 100; // 内存值

    private static AtomicIntegerFieldUpdater<AtomicExample4> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample4.class, "count");

    public static void main(String[] args) {
        AtomicExample4 atomicExample4 = new AtomicExample4();

        // CAS，V = 100 ，A = 100，则把 B = 120 赋值给 V，即 V = 120。
        if (updater.compareAndSet(atomicExample4, 100, 120)) {
            log.info("update success 1, {}",atomicExample4.getCount());
        }

        if (updater.compareAndSet(atomicExample4, 100, 120)) {
            log.info("update success 2, {}", atomicExample4.getCount());
        } else {
            log.info("update failed, {}", atomicExample4.getCount());
        }
    }

}
