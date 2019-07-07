package com.coolron.thread.ticket;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Date: 2018/11/27 11:11
 * @Description:
 *
 * n 张火车票 每张有一个编号 同时 10 个窗口对外售票
 *
 * 代码分析：
 * 下面代码 判断 和 销售(poll()方法) 任然不是原子性的 但是不会出问题
 *
 */
public class TicketSeller4 {

    // 并发容器 队列
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) tickets.add("票号：" + i);
        // queue 中不可以装null 值
        // tickets.add(null);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true){
                    // 往外拿值  没有 返回为 null
                    String s = tickets.poll();

                    /**
                     * 这里的操作 和 判断不是原子性的 但是不会出问题
                     * 分析： 我们做完判断之后再也没有对 队列做任何的修改操作
                     * 上一个案例中是判断之后 对容器做了修改操作 所以判断和操作必须原子性
                     */

                    if (s == null) break;
                    else System.out.println("售票：" + s);
                }
            }).start();
        }
    }
}
