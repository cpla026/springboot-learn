package com.coolron.thread.synchronize;

/**
 * @Auther: xf
 * @Date: 2018/11/24 15:17
 * @Description: 不要以字符串常量作为锁对象
 * 下面其实锁定的是同一个对象
 */
public class T11 {

    // str1 str2 是同一个对象
    String str1 = "hello";
    String str2 = "hello";

    void test01(){
        synchronized (str1) {  // 锁定的是 “hello” 并不是 str1

        }
    }

    void test02(){
        synchronized (str2) {

        }
    }

}
