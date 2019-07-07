package com.coolron.lambda.funinterface;

/**
 * @Auther: xf
 * @Date: 2019/1/22 14:40
 * @Description:
 */
public class FunctionInterface2Test {

    public static void main(String[] args) {
         /*func1(new FunctionInterface1() {
            @Override
            public void test() {
                System.out.println(">>>>>>>>>>>>>FunctionInterface1");
            }
        });*/

        /**
         * 使用lambda 表达式代替上文中的 匿名内部类
         */
        //func2((int e) -> System.out.println(">>>>>>>>>>>>>FunctionInterface2 : " + e));

    }
    private static void func2(FunctionInterface2 functionInterface2){
        int x = 1;
        functionInterface2.test(x);
    }
}
