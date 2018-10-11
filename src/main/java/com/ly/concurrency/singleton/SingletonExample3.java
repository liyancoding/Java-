package com.ly.concurrency.singleton;

import com.ly.concurrency.annoations.NotRecommend;
import com.ly.concurrency.annoations.NotThreadSafe;
import com.ly.concurrency.annoations.ThreadSafe;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 20:44 2018/10/11
 * @Modified By:
 */

/**
 * 懒汉模式----线程不安全
 * 单例实例在第一次使用时进行创建
 * 缺点：每次调用方法都要同步操作，性能低下
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    // 私有构造函数
    private SingletonExample3() {

    }

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态的工厂方法
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
