package com.coolron.lambda.funinterface;

import java.util.function.Predicate;

/**
 * @Auther: xf
 * @Date: 2019/1/22 14:42
 * @Description: 判断分数是否及格
 *
 * 此例中：
 * lambda表达式：将判断分数是否及格的逻辑封装 传递到函数接口中
 *
 * 函数接口：返回逻辑的判断结果
 *
 */
public class FunctionInterface4Test {
    public static void main(String[] args) {
        /*boolean b = func4(50, new FunctionInterface4<Integer>() {

            @Override
            public boolean isPass(Integer score) {
                return score >= 60 ? true : false;
            }
        });*/

        /**
         * 使用lambda 表达式代替上文中的 匿名内部类
         *
         * lambda 表达式代替的只是 接口函数的实现
         */
        //boolean b = func4(50, (e) -> { return e >= 60 ? true : false; });
        Predicate<Integer> predicate = (e) -> { return e >= 60 ? true : false; };
        boolean b = func(60, predicate);

        //boolean b = func(60, (e) -> { return e >= 60 ? true : false; });
        //func4((e) -> true);

        System.out.println(">>>>>>>>>>> : " + b);
    }

    /**
     *
     * @param score  分数
     * @param functionInterface4  函数接口
     * @return 返回Boolean值
     */
    private static boolean func4(int score, FunctionInterface4<Integer> functionInterface4){
        boolean test = functionInterface4.isPass(score);
        return test;
    }

    private static boolean func(int score, Predicate<Integer> predicate) {
        return predicate.test(score);
    }

}
