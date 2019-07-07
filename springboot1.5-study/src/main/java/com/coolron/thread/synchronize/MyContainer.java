package com.coolron.thread.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/24 15:33
 * @Description:
 * 实现一个容器 提供两个方法 add size
 * 写两个线程 线程1 添加10 个元素到容器中 线程2 实现容器元素监控 容器大小到5时 通知
 *
 *
 */
public class MyContainer {

    // 添加 volatile 使 t2 能够收到通知
    /*volatile*/ List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }
    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer container = new MyContainer();
        new Thread(()->{
            for (int i=0; i<10; i++){
                container.add(new Object());
                System.out.println("add: " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            while (true){
                if(container.size() == 5){
                    break;
                }
            }
            System.out.println("t2结束");
        },"t2").start();
    }
}
