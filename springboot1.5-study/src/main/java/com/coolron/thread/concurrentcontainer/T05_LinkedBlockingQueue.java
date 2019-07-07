package com.coolron.thread.concurrentcontainer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/27 16:20
 * @Description:
 *
 * Queue:
 *   ConcurrentLinkedQueue  并发加锁
 *   BlockingQueue  阻塞式
 *   DelayQueue 执行定时任务
 *
 */
public class T05_LinkedBlockingQueue {

    // 无界队列  可以一直往里面加  直到内存爆了
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
    static Random r = new Random();

    public static void main(String[] args) {

        // 一个生产者
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i);  // 阻塞式容器中 put 如果满了 就等待
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        // 5个消费者
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for(;;){
                    try {
                        // 阻塞式容器中 take 如果空了 就等待
                        System.out.println(Thread.currentThread().getName() + "take" + strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }
    }
}
