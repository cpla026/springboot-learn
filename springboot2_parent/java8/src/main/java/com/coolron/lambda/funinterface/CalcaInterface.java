package com.coolron.lambda.funinterface;

/**
 * @Auther: xf
 * @Date: 2019/1/10 14:31
 * @Description: 函数接口
 * 只有一个方法的接口。作为Lambda表达式的类型
 */
@FunctionalInterface
public interface CalcaInterface {
    /**
     * 有且仅有一个方法
     * @param x
     * @param y
     * @return
     */
    public int cala(int x, int y);
}
