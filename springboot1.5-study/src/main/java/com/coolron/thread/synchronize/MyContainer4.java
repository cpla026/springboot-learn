package com.coolron.thread.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 优化 MyContainer
 *
 * @Auther: xf
 * @Date: 2018/11/24 15:33
 * @Description: 实现一个容器 提供两个方法 add size
 * 写两个线程 线程1 添加10 个元素到容器中 线程2 实现容器元素监控 容器大小到5时 通知
 * <p>
 * 最简洁的方法
 * <p>
 * 使用 门闩 latch 替代 wait notify 来进行通知，
 * 好处是通讯方式简单 同时也可以指定等待时间
 * 使用 await() 和 countDown() 方法替代wait 和 notify
 *
 * CountDownLatch 不涉及锁定， 当count的值为0 时当前线程继续运行
 * 当不涉及到同步，只是线程间的通讯的时候 使用 synchronized + wait/notify 就太重了
 * 这时就应该考虑 CountDownLatch、CyclicBarrier、Semaphore
 */
public class MyContainer4 {

    // 添加 volatile 使 t2 能够收到通知
    volatile List lists = new ArrayList();

    public static void main(String[] args) {
        MyContainer4 container = new MyContainer4();

        // 指定门闩, CountDown 的门闩 CountDown 是往下数 当1 变成 0 的时候门闩就打开了
        CountDownLatch latch = new CountDownLatch(1);

        // t2
        new Thread(() -> {
            System.out.println("t2 启动");
            if (container.size() != 5) {
                try {
                    // 门闩等待  等在这里等门打开
                    latch.await();

                    // 也可以指定等待时间
                   // latch.await(5000,TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // t1
        new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                System.out.println("add:" + i);

                if (container.size() == 5) {
                    // 打开门闩 让t2 得以执行
                    latch.countDown(); // 调用 countDown方法让指定的 1 减到 0 即门闩打开了

                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1结束");
        }, "t1").start();
    }

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }
}
