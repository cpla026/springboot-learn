package com.coolron.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/24 11:11
 * @Description:
 *
 */
public class T7 {

    int count = 10;

    synchronized void test01(){
        System.out.println("test01");

        while(true){
            count ++;
            System.out.println(Thread.currentThread().getName()+count);
            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 15) {
                int i = 1 / 0;  // 抛出异常 锁会被释放 如果有异常不想释放锁，可以在此处 catch
            }
        }
    }

    public static void main(String[] args) {
        T7 t = new T7();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.test01();
            }
        };
        new Thread(r, "t1").start();  // t1 不释放锁  t2 不会被执行

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r, "t2").start();
    }
}
