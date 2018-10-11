package com.ly.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 15:29 2018/10/11
 * @Modified By:
 */

/**
 * 线程安全的类用这个注解标注一下
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface TheadSafe {
    String value() default "";
}
