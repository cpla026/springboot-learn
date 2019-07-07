package com.coolron.thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 手动锁  可重入锁
 * @Auther: xf
 * @Date: 2018/11/26 10:06
 * @Description:
 *
 * ReentrantLock 替换 synchronized 完成同样的功能
 *
 * 必须要手动释放锁
 * 使用 synchronized 遇到异常 jvm 会自动释放锁 但是 Lock需要手动释放锁
 *
 *
 *
 */
public class ReentrantLock2 {

    Lock lock = new ReentrantLock();
    // 锁定的是 this 对象
     void test01(){
         try {
             lock.lock();  // synchronized(this)
             for (int i = 0; i < 10; i++) {
                 TimeUnit.SECONDS.sleep(1);
                 System.out.println(i);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
             lock.unlock();
         }

    }

     void test02(){
         lock.lock();
        System.out.println("test02......");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock2 r1 = new ReentrantLock2();
        new Thread(r1::test01,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::test02,"t2").start();
    }
}
