package com.coolron.thread.interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: xf
 * @Date: 2018/11/26 12:39
 * @Description:
 * 面试题：
 * 写一个固定容量同步容器， 拥有put 和 get 方法 以及 getCount 方法
 * 能够支持 2 个生产者线程及10个消费者线程的阻塞调用
 * 使用wait notify notifyAll 调用
 *
 * 使用 Lock 和 Condition 实现
 * Condition 可以更精确的指定哪些线程被唤醒
 */
public class MyContainer2<T> {
    final private LinkedList<T> lists = new LinkedList<T>();
    final private int MAX = 10;
    private int count = 10;

    private Lock lock = new ReentrantLock();
    // 条件
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    public void put(T t){

        try {
            lock.lock();
            while (lists.size() == MAX) {
                producer.await();
            }
            lists.add(t);
            ++count;
            consumer.signalAll(); // 通知消费者线程进行消费
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public T get(){
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0){ // 容器空了 就通知生产者生产
                consumer.await();
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll();  //通知生产者进行生产

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();
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
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            },"p" + i).start();
        }
    }
}
