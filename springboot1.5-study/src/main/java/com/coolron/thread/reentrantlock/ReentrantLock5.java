package com.coolron.thread.reentrantlock;

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
 * reentrantlock 默认不公平锁 还可以指定为公平锁(谁等待时间长 谁获取锁)  效率低
 * synchronized 为不公平锁(竞争式的获取锁，与等待时间无关)  效率高
 *
 *
 */
public class ReentrantLock5 extends Thread{

    // 参数为true 公平锁 t1 t2 一人一次
    private static Lock lock = new ReentrantLock(true);

    public void run(){
        for (int i = 0; i < 10; i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 r1 = new ReentrantLock5();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();
    }
}
