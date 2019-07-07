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
 * 使用reentrantlock 可以调用lockInterruptibly 方法，可以对线程 interrupt 方法做出响应
 * 在一个线程等待的过程中 可以被打断
 *
 */
public class ReentrantLock4 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        // t1 长时间占据 lock
        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);  // 几乎睡死了  执行不完
                System.out.println("t2 end");

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        t1.start();

        // t2 需要申请 到 lock 可是lock 被 t1 长时间占据
        Thread t2 = new Thread(()->{
            try {
                //lock.lock();  // 不可以被打断
                lock.lockInterruptibly();  // 可以对interrupt() 方法做出响应
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("interrupted");
            }finally {
                //lock.unlock();
            }
        });
        t2.start();

        // 主线程 可以通知 t2 线程 不要等到了
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();  // 打断线程2 的等待
    }
}
