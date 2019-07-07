package com.coolron.thread.singleton;

import java.util.Arrays;

/**
 * 线程安全的单例模式
 * @Auther: xf
 * @Date: 2018/11/27 10:12
 * @Description:
 *
 * http://www.cnblogs.com/xudong-bupt/p/3433643.html
 *
 * 单例模式在内存中永远只有一个对象
 *
 * 下面这种方式既不用加锁 也实现了懒加载
 *
 */
public class Singleton {

    private Singleton(){
        System.out.println("single");
    }

    private static class Inner{
        private static Singleton singleton = new Singleton();
    }

    private static Singleton getSingle(){
        return Inner.singleton;
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[200];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(()->{
                Singleton.getSingle();
            });
        }
        Arrays.asList(ths).forEach(o->o.start());
    }
}
