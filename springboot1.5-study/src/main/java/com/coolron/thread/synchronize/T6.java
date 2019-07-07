package com.coolron.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/24 11:11
 * @Description:
 * 同步方法可以调用另外一个同步方法，一个线程已经拥有了某个对象的锁
 * 再次申请的时候任然会得到改对象的锁  即 锁是可以重入的
 */
public class T6 {

    synchronized void test01(){
        System.out.println("test01");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test02();
    }

    synchronized void test02(){
        System.out.println("test02");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
