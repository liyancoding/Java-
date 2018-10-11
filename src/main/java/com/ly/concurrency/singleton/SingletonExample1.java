package com.ly.concurrency.singleton;

import com.ly.concurrency.annoations.ThreadSafe;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 20:44 2018/10/11
 * @Modified By:
 */

/**
 * 饿汉模式----线程安全
 * 单例实例在类装载时进行创建
 * 缺点：性能低，如果实例没有被使用，会造成资源浪费
 */
@ThreadSafe
public class SingletonExample1 {
    // 私有构造函数
    private SingletonExample1() {

    }

    // 单例对象
    private static SingletonExample1 instance = new SingletonExample1();

    // 静态的工厂方法
    public static SingletonExample1 getInstance() {
        return instance;
    }
}
