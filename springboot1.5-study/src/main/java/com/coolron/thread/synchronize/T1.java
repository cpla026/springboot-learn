package com.coolron.thread.synchronize;

/**
 * @Auther: xf
 * @Date: 2018/11/24 10:08
 * @Description:  对某个对象加锁 锁定的是某个对象
 *  所的粒度要细  不然效率不高
 *  java 锁的是堆  不可以锁栈
 */
public class T1 {

    public int count = 10;
    Object o = new Object();
    public void test(){
        // synchronized 锁定的是 this ，并不是下面的代码块  下一个进程必须等待上一个执行完毕才能执行
        synchronized (o) {  // 任何对象要执行下面代码必须先拿到 o 的锁
        //synchronized (this) {  // 任何对象要执行下面代码必须先拿到 this 的锁
            count++;
            System.out.println(Thread.currentThread().getName() + ": " +count);
        }
    }
}

