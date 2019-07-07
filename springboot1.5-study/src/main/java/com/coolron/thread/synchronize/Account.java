package com.coolron.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/24 10:54
 * @Description:
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读问题
 */
public class Account {
    private String name;
    private double balance;  // 默认 0

    // 写 加上锁
    public synchronized void set(String name, double balance){
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    // 读 不加锁 容易脏读
    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(()->account.set("ron",20.0)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("ron"));  // 结果：0.0
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("ron"));  // 结果： 20.0
    }
}
