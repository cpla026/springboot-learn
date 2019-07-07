package com.coolron.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/24 11:11
 * @Description:
 * volatile 使一个变量在多个线程间可见
 * A B 都使用到一个变量， Java 默认是 A 线程中保留一份copy B 修改了 A 未必知道
 * 使用 volatile 关键字 会使所有的线程都知道变量的改变
 *
 * volatile并不能保证多个线程共同修改running 变量时所带来的不一致问题 即 volatile 不能替代 synchronized
 *
 */
public class T8 {

    /*volatile*/ boolean running = true;

    void test01(){
        System.out.println("test01 start");

        while(running){

        }
        System.out.println("test01 end");
    }

    public static void main(String[] args) {
        T8 t = new T8();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.test01();
            }
        };
        new Thread(r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
