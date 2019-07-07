package com.coolron.lambda.funinterface;

/**
 * @Auther: xf
 * @Date: 2019/1/22 11:02
 * @Description:
 */
public class Test2 {

    public static void main(String[] args) {

        /**
         * expression = (variable) -> action
         * 将一个匿名内部类作为参数传递
         * Runnable：实际是一个只包含一个方法的接口 — 函数接口
         *
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        }).start();

        /**
         * Runnable：实际是一个只包含一个方法的接口 — 函数接口
         * 使用lambda表达式可以代替上文的匿名内部类
         */
        new Thread(() -> System.out.println("Hello World!")).start();

    }
}
