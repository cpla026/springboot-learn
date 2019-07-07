package com.coolron.thread.synchronize;

/**
 * @Auther: xf
 * @Date: 2018/11/24 10:08
 * @Description: 简化  等价与 T1
 */
public class T3 {

    public static int count = 10;

    public static synchronized  void test() {
        count++;
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }

    public static void test2() {
        // 此处不可以使用 this 静态的方法不需要new 对象访问
        // 当你锁定一个静态方法的时候 锁定的是当前类的Class 对象
        synchronized(T3.class){
            count++;
            System.out.println(Thread.currentThread().getName() + ": " + count);
        }
    }

}

