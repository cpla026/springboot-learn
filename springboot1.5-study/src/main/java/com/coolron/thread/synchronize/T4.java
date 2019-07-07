package com.coolron.thread.synchronize;

/**
 * @Auther: xf
 * @Date: 2018/11/24 10:08
 * @Description:
 */
public class T4 implements Runnable {

    public int count = 10;

    // 原子操作
    public synchronized  void run() {
        count++;
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }

    public static void main(String[] args) {
        T4 t = new T4();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }

    /*
    * run() 不加锁 会出现
    * Thread0: 12
    * Thread2: 13
    * Thread1: 12
    * Thread3: 14
    * Thread4: 15
    * run() 加锁 正常
    * */
}

