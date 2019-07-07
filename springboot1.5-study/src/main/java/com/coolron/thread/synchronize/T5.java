package com.coolron.thread.synchronize;

/**
 * @Auther: xf
 * @Date: 2018/11/24 10:08
 * @Description:  同步方法 和 非同步方法 能否同时执行
 */
public class T5 {

    public synchronized  void test01() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "test01");
    }

    public void test02(){
        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "test02");
    }
    public static void main(String[] args) {
        T5 t = new T5();
        // 执行 test01 的时候 test02 可以被执行
        // t2test02
        // t1test01
//        new Thread(() -> t.test01(),"t1").start();
//        new Thread(() -> t.test02(),"t2").start();

//        new Thread(t::test01, "t1").start();
//        new Thread(t::test02, "t2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t.test01();
                t.test02();
            }
        }).start();

    }
}

