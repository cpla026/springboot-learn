package com.coolron.thread.concurrentcontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/27 16:01
 * @Description:
 */
public class T03_SynchronizedList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(list);

    }

}
