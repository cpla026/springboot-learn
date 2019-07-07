package com.coolron.thread.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/26 14:09
 * @Description:
 *
 * ThreadLocal 使用空间换时间   效率更高
 * synchronized 使用时间换空间  上锁 其他线程只能等待
 *
 * ThreadLocal 线程局部变量
 * 自己线程的变量 自己使用 其他线程不影响
 *
 */
public class ThreadLocal2 {

    // 只能存储自己线程的变量
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 打印出来为 null
            System.out.println(tl.get());
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }

    static class Person{
        String name = "ron";
    }

}


