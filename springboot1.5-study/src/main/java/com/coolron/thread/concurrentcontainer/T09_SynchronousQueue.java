package com.coolron.thread.concurrentcontainer;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Auther: xf
 * @Date: 2018/11/27 17:18
 * @Description: 同步队列
 *
 */
public class T09_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        // 容量为0 消息必须马上消费掉 不消费立马出问题
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");  // 阻塞  等待消费者消费  不可以往容器中放  容器 为 0
        //strs.add("aaa");   // 报错   add 不进去
        System.out.println(strs.size());
    }
}
