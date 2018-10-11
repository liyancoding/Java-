package com.ly.concurrency.publish;

import com.ly.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Author: LiYan
 * @Description:
 * @Date: Created in 20:32 2018/10/11
 * @Modified By:
 */

@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] strings = {"a", "b", "c"};

    public String[] getStrings() {
        return strings;
    }

    public static void main(String[] args){
        UnsafePublish up = new UnsafePublish();

        log.info("{}", Arrays.toString(up.getStrings()));

        up.getStrings()[0] = "d";

        log.info("{}",Arrays.toString(up.getStrings()));
    }
}
