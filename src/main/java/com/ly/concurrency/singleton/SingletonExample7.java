package com.ly.concurrency.singleton;

import com.ly.concurrency.annoations.Recommend;
import com.ly.concurrency.annoations.ThreadSafe;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 20:44 2018/10/11
 * @Modified By:
 */

/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有构造函数
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE();

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }

    public static void main(String[] args){
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }

}
