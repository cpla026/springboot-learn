package com.coolron.lambda.funinterface;

/**
 * @Auther: xf
 * @Date: 2019/1/22 18:44
 * @Description: 自定义 函数接口
 * @FunctionalInterface 可不写，写了之后会限定只能有一个抽象方法
 * 可有其他默认方法 即default修饰
 */
@FunctionalInterface
public interface FunctionInterface1 {

    /**
     * 函数接口测试
     * 无参 无返回值
     */
    void test();
}
