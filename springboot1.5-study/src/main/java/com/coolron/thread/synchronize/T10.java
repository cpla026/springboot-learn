package com.coolron.thread.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: xf
 * @Date: 2018/11/24 11:11
 * @Description:
 * 解决 T9 中同样的问题 使用 AtomicXXX 类
 * AtomicXXX类本身方法都是原子性的， 但不能保证多个方法连续调用时原子的
 */
public class T10 {

    /*volatile*/ //int count = 0;

    // 原子性操作的 int 类型
    AtomicInteger count = new AtomicInteger(0);

    // 不需要 synchronized
    /*synchronized*/ void test01(){
       for (int i=0; i<1000; i++)
           // if(count.get() < 500)  // 加上这句就不具备原子性了  get()是原子的  incrementAndGet() 是原子的   但不能保证多个方法连续调用时原子的
           count.incrementAndGet(); // 替代 count++（不具备原子性）
    }

    public static void main(String[] args) {
        T10 t = new T10();

        List<Thread> threads = new ArrayList<Thread>();
        for(int i=0; i<10; i++){
            threads.add(new Thread(t::test01,"thread." + i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)-> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(Thread.currentThread().getName() + ":" + t.count);
    }
}
