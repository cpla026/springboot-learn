package com.coolron.lambda.funinterface;

/**
 * @Auther: xf
 * @Date: 2019/1/22 13:47
 * @Description:
 */
public class CalcaInterfaceTest {

    public static void main(String[] args) {
        // 之前的做法  类实现接口：加法、乘法 分别实现一套代码

        // 函数接口的做法
        //CalcaInterface c1 = (int x,int y) -> {return x + y;};
        CalcaInterface c1 = (x,y) -> x + y;
        CalcaInterface c2 = (x,y) -> x * y;

        System.out.println(c1.cala(3,4)); // 7
        System.out.println(c2.cala(3,4)); // 12

    }
}
