package com.coolron.thread.ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2018/11/27 11:11
 * @Description:
 *
 * n 张火车票 每张有一个编号 同时 10 个窗口对外售票
 *
 * 下面程序问题：
 * 重复销售？ 超量销售？
 */
public class TicketSeller1 {

    // 不是同步的  包括它的方法  remove... 都不是同步的
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(tickets.size() > 0){
                    System.out.println("售票：" + tickets.remove(0));
                }
            }).start();
        }
    }
}
