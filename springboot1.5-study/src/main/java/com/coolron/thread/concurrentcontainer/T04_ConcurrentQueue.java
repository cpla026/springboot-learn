package com.coolron.thread.concurrentcontainer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Auther: xf
 * @Date: 2018/11/27 16:05
 * @Description:
 * 队列   并发容器中使用最多的
 *
 */
public class T04_ConcurrentQueue {

    public static void main(String[] args) {
        // 单向队列
        Queue<Object> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            boolean offer = strs.offer("a" + i);  // add 可以根据返回值判断是否加入成功
        }
        System.out.println(strs);         // [a0, a1, a2, a3, a4, a5, a6, a7, a8, a9]
        System.out.println(strs.size());  // 10

        System.out.println(strs.poll());  // a0
        System.out.println(strs.size());  // 9

        System.out.println(strs.peek());  // a1
        System.out.println(strs.size());  // 9

        // 双向队列
        //Queue<Object> strs = new ConcurrentLinkedDeque<>();
    }
}
