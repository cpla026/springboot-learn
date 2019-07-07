package com.coolron.thread.concurrentcontainer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Auther: xf
 * @Date: 2018/11/27 15:32
 * @Description: 并发容器
 * 写时复制容器 copy on write
 * 多线程环境下， 写时效率低， 读时效率高
 * 适合写少读多
 *
 */
public class T02_CopyOnWriteList {

    public static void main(String[] args) {
        //List<String> lists = new ArrayList<>();  // 并发问题
        //List<String> lists = new Vector<>();
        List<String> lists = new CopyOnWriteArrayList<>();

        Random r = new Random();
        Thread[] ths = new Thread[100];

        for (int i = 0; i < ths.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a" + r.nextInt(100000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }
        
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    private static void runAndComputeTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
