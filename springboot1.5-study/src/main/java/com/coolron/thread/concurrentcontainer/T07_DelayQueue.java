package com.coolron.thread.concurrentcontainer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/27 16:43
 * @Description:
 */
public class T07_DelayQueue {

    // 无界队列  可以将往里面加的东西理解为一个任务
    // 什么时候可以往外拿，只有等待一段时间之后才可以， 每个元素自己记录还有多长时间可以被消费者拿走
    // 默认排好顺序 等待时间最长 拍最前面
    // 可以定时执行任务
    static BlockingQueue<MyTask> tasks = new DelayQueue<>();
    static Random r = new Random();

    static class MyTask implements Delayed{
        long runningTime;

        MyTask(long rt){
            this.runningTime = rt;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        // 还有多长时间可以往外拿
        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
            return 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask(now + 1000);  // 一秒之后执行
        MyTask t2 = new MyTask(now + 2000);  // 两秒之后执行
        MyTask t3 = new MyTask(now + 3000);
        MyTask t4 = new MyTask(now + 4000);
        MyTask t5 = new MyTask(now + 5000);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);
        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
