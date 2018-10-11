package com.ly.concurrency.publish;

import com.ly.concurrency.annoations.NotRecommend;
import com.ly.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 20:37 2018/10/11
 * @Modified By:
 */

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args){
        new Escape();
    }
}
