package com.ly.concurrency.annoations;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 15:34 2018/10/11
 * @Modified By:
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 线程不安全的类用这个注解标注一下
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {
    String value() default "";
}
