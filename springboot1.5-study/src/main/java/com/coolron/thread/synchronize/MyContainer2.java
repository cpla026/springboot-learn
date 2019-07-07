package com.coolron.thread.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 优化 MyContainer
 * @Auther: xf
 * @Date: 2018/11/24 15:33
 * @Description:
 * 实现一个容器 提供两个方法 add size
 * 写两个线程 线程1 添加10 个元素到容器中 线程2 实现容器元素监控 容器大小到5时 通知
 *
 * 给 lists 添加 volatile 后 t2 可以收到通知，但是 t2线程的while死循环浪费CPU
 *
 * 使用 wait 和 notify wait会释放锁 notify 不会释放锁(notify 是其他线程调用的)
 * 注意：必须让 t2 先执行，即让 t2 先监听才可以。
 *
 * 下面代码结果：
 * 可以看到输出结果并不是size=5时t2 退出 而是t1 结束时 t2 才接到通知
 */
public class MyContainer2 {

    // 添加 volatile 使 t2 能够收到通知
    volatile List lists = new ArrayList();


    public void add(Object o){
        lists.add(o);
    }
    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer2 container = new MyContainer2();

        final Object lock = new Object();
        new Thread(()->{
            synchronized (lock){
                System.out.println("t2 启动");
                if(container.size() != 5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 启动");
            synchronized (lock){
                for (int i=0; i<10; i++){
                    container.add(new Object());
                    System.out.println("add:" + i);

                    if (container.size() == 5){
                        // 叫醒第一个线程
                        lock.notify();
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t2结束");
        },"t1").start();
    }
}
