package com.coolron.thread.ticket;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Date: 2018/11/27 11:11
 * @Description:
 *
 * n 张火车票 每张有一个编号 同时 10 个窗口对外售票
 *
 * 下面代码相当于将销售和判断加到一个原子操作里面了
 * 不会出问题
 * 但是每卖一张票就锁定  效率不高
 */
public class TicketSeller3 {

    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 1000; i++) tickets.add("票号：" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true){
                    synchronized (tickets){
                        if(tickets.size() <= 0) break;

                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("售票：" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
