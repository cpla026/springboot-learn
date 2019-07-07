package com.coolron.thread.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/26 14:09
 * @Description:
 * ThreadLocal 线程局部变量
 *
 */
public class ThreadLocal1 {

    volatile static Person p = new Person();

    public static void main(String[] args) {

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "swang";
        }).start();
    }

}

class Person{
     String name = "ron";
}
