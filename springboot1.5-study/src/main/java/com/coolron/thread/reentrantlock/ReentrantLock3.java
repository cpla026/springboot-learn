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
 * 使用reentrantlock 可以尝试锁定 trylock 这样无法锁定或者指定时间内无法锁定的时候 线程就可以决定是否继续等待
 *
 */
public class ReentrantLock3 {

    Lock lock = new ReentrantLock();
    // 锁定的是 this 对象
     void test01(){
         try {
             lock.lock();  // synchronized(this)
             for (int i = 0; i < 5; i++) {
                 TimeUnit.SECONDS.sleep(1);
                 System.out.println(i);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
             lock.unlock();
         }

    }

    /**
     * 进行尝试锁定  不管锁定与否 方法都将继续执行
     * 可以根据 tryLock 的返回值判断是否锁定
     * 可以指定 tryLock 的时间，由于 tryLock(time) 抛出异常， 所以注意 unlock 的处理
     */
     void test02(){

         //
         /*boolean locked = lock.tryLock();
         System.out.println("test01()>>>>locked:" + locked);
         if(locked) lock.unlock();*/

         // 第二种方式
         boolean locked = false;
         try {
             locked = lock.tryLock(5, TimeUnit.SECONDS);
             System.out.println("test02:locked>>>" + locked);
         } catch (Exception e) {
            e.printStackTrace();
         }finally {
            if(locked) lock.unlock();
         }

    }

    public static void main(String[] args) {
        ReentrantLock3 r1 = new ReentrantLock3();
        new Thread(r1::test01,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::test02,"t2").start();
    }
}
