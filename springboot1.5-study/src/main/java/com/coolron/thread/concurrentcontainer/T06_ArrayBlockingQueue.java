package com.coolron.thread.concurrentcontainer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/27 16:20
 * @Description:
 *
 */
public class T06_ArrayBlockingQueue {

    // 有界队列  只能装 10 个
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        // 上面已经将容器加满了 测试下面方法
        strs.put("aaa");  // 满了就会等待  程序阻塞
        strs.add("aaa");  // 异常
        strs.offer("aaa"); // 加不进去 没有异常
        strs.offer("aaa", 1, TimeUnit.SECONDS);  // 一秒钟加不进去就不加了

        System.out.println(strs);
    }
}
