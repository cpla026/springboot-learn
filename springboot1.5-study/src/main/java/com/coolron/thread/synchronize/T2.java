package com.coolron.thread.synchronize;

/**
 * @Auther: xf
 * @Date: 2018/11/24 10:08
 * @Description: 简化  等价与 T1
 */
public class T2 {

    public int count = 10;

    public synchronized void test() {
        count++;
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }
}

