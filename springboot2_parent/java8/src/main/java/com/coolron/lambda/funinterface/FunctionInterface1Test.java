package com.coolron.lambda.funinterface;

import com.coolron.lambda.Person;

/**
 * @Auther: xf
 * @Date: 2019/1/22 14:38
 * @Description:
 */
public class FunctionInterface1Test {

    public static void main(String[] args) {
        /*func1(new FunctionInterface1() {
            @Override
            public void test() {
                System.out.println(">>>>>>>>>>>>>FunctionInterface1");
            }
        });*/

        /**
         * 使用lambda 表达式代替上文中的 匿名内部类
         * -> 将参数和实现逻辑分离  逻辑即函数接口中方法需要执行的逻辑
         */
        func1(() -> System.out.println(">>>>>>>>>>>>>FunctionInterface1"));

        FunctionInterface1 functionInterface1 = Person::new;
    }

    private static void func1(FunctionInterface1 functionInterface1){
        functionInterface1.test();
    }

}
