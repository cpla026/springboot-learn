package com.coolron.lambda.funinterface;

/**
 * @Auther: xf
 * @Date: 2019/1/22 10:44
 * @Description: 自定义 函数接口
 */
@FunctionalInterface
public interface FunctionInterface4<T> {

    /**
     * 函数接口测试
     * 有参 有返回值
     */
    boolean isPass(T x);

}
