package com.coolron.thread.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/24 11:11
 * @Description:
 * volatile 并不能保证多个线程共同修改running 变量时所带来的不一致问题 即 volatile 不能替代 synchronized
 * volatile  synchronized 的区别：
 * volatile 可见性  synchronized 原子性 和 可见性
 * volatile 效率比 synchronized 高
 * 所以值需要保证可见性的时候 使用 volatile
 */
public class T9 {

    // 加到 10000 与预期结果不一致
    /*volatile*/ int count = 0;

    // 使用 synchronized 解决
    synchronized void test01(){
       for (int i=0; i<1000; i++)
           count++;
    }

    public static void main(String[] args) {
        T9 t = new T9();

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
