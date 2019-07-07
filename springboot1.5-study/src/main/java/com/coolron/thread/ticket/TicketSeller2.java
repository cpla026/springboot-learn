package com.coolron.thread.ticket;

import java.util.Vector;

/**
 * @Date: 2018/11/27 11:11
 * @Description:
 *
 * n 张火车票 每张有一个编号 同时 10 个窗口对外售票
 *
 * 下面程序问题：
 * 重复销售？ 超量销售？
 *
 * 问题原因分析：
 * 如果剩最后一张票了，虽然size() remove() 是原子性的，但是可能还有许多线程通过了size() > 0 的判断，
 * 其中有一个线程抢到了这一张票，那么其他的线程执行 remove() 就会出问题了
 */
public class TicketSeller2 {

    // Vector 同步的容器 包括所有的方法 都是同步的   不过下面代码依旧会出问题
    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) tickets.add("票号：" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(tickets.size() > 0){

                    /**
                     *  Vector 的size() remove() 方法都是原子性的  可以操作和判断分离了
                     *  判断 和 操作中间就不可以保证原子性了
                     */

                   /* try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/

                    System.out.println("售票：" + tickets.remove(0));
                }
            }).start();
        }
    }
}
