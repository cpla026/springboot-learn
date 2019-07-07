package com.coolron.thread.concurrentcontainer;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: xf
 * @Date: 2018/11/27 14:47
 * @Description: 并发容器
 *
 * 对于 map、set 的使用总结：
 * 1. 不加锁
 * hashmap
 * treemap
 * linkedhashmap
 *
 * 2. 加锁
 * 并发不高可以使用 hashTable  或者 hashMap + Collections.synchronizedMap(map)
 * 并发高：ConcurrentHashMap  需排序 ConcurrentSkipListMap
 *
 */
public class T01_ConcurrentMap {

    public static void main(String[] args) {

        // Set(key) 的情况同 Map(key-value)
        //Map<String, String> map = new ConcurrentHashMap<>();   // 加锁了  容器分成了 16 段 每次锁定的是16段中的一段  大锁分成小锁 所以效率比 HashTable 高
        //Map<String, String> map = new ConcurrentSkipListMap<>();  // 跳表数据结构  高并发 并且排序

        //Map<String, String> map = new Hashtable<>();  // 最老的 map 实现 默认加了锁的 锁定的是整个容器 效率低
        Map<String, String> map = new HashMap<>();  // 手动加锁  Collections.synchronizedXXX
        // 返回一个加了锁的map   等同 hashTable
        // Map<String, String> synchronizedMap = Collections.synchronizedMap(map);


        // Map<String, String> map = new TreeMap<>();

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000),"a" + r.nextInt(100000));
                }
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(t->t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
