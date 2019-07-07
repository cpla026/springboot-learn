package com.coolron.thread.reentrantlock;

import java.util.concurrent.TimeUnit;

/** 手动锁  可重入锁
 * @Auther: xf
 * @Date: 2018/11/26 10:06
 * @Description:
 * ReentrantLock 替换 synchronized
 *
 */
public class ReentrantLock1 {

    // 锁定的是 this 对象
    synchronized void test01(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    synchronized void test02(){
        System.out.println("test02......");
    }

    public static void main(String[] args) {
        ReentrantLock1 r1 = new ReentrantLock1();
        new Thread(r1::test01).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
