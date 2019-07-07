package com.coolron.thread.interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/26 12:39
 * @Description:
 * 面试题：
 * 写一个固定容量同步容器， 拥有put 和 get 方法 以及 getCount 方法
 * 能够支持 2 个生产者线程及10个消费者线程的阻塞调用
 * 使用wait notify notifyAll 调用
 */
public class MyContainer1<T> {
    final private LinkedList<T> lists = new LinkedList<T>();
    final private int MAX = 10;
    private int count = 10;

    public synchronized void put(T t){
        // 为什么使用while 而不是 if
        // if 只会判断一次 while 每次执行都会判断一次
        while (lists.size() == MAX){   // 容器满了就停止   通知消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        // 不用 notify() 只叫醒一个线程  万一叫醒的是现在在这里wait() 的线程 就死锁了(因为其他的线程都死了)
        this.notify(); // 通知消费者线程进行消费
    }

    public synchronized T get(){
        T t = null;
        while (lists.size() == 0){ // 容器空了 就通知生产者生产
            try {
                this.wait();    // wait 99.99% 都和 while一起使用
            } catch (Exception e){

            }
        }
        t = lists.removeFirst();
        count++;
        this.notifyAll();  //通知生产者进行生产
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        // 启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            },"c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + "" + j);
                }
            },"p" + i).start();
        }
    }
}
