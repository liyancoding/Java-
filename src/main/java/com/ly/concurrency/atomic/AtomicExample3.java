package com.ly.concurrency.atomic;

import com.ly.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 19:00 2018/10/11
 * @Modified By:
 */

@Slf4j
@ThreadSafe
public class AtomicExample3 {

    private static AtomicReference<Integer> count = new AtomicReference<>(1);

    public static void main(String[] args) {
        // 执行CAS操作,内存值 V = 1，旧的预期值 A = 1，更新值 B = 5，当 V = A 时，将 B 赋值给 V
        count.compareAndSet(1, 5); // 5
        count.compareAndSet(1, 2); // no
        count.compareAndSet(3, 8); // no
        count.compareAndSet(5, 10); // 10
        count.compareAndSet(9, 9); // no

        log.info("count = {}", count);
    }
}
