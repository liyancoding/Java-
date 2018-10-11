package com.ly.concurrency.singleton;

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
 */
@NotThreadSafe
public class SingletonExample2 {
    // 私有构造函数
    private SingletonExample2() {

    }

    // 单例对象
    private static SingletonExample2 instance = null;

    // 静态的工厂方法
    public static SingletonExample2 getInstance() {
        if (instance == null) {
            instance = new SingletonExample2();
        }
        return instance;
    }
}
