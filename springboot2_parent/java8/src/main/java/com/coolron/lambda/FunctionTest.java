package com.coolron.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @Auther: xf
 * @Date: 2019/1/10 10:08
 * @Description: Function 接口
 *
 * Funcation 执行转换操作，输入类型 T 的数据，返回 R 类型的结果
 *
 * Function 接口有一个参数并且返回一个结果，并附带了一些可以和其他函数组合的默认方法（compose, andThen） 形成复合Funtion（有输入，有输出）
 *
 *
 *
 * compose 和 andThen 的不同之处是函数执行的顺序不同。
 * compose 函数先执行参数，然后执行调用者，
 * andThen 先执行调用者，然后再执行参数。 所以说明andThen  先执行name后执行square
 *
 * Supplier接口:
 * 返回一个给定类型的结果，与Function不同的是，Supplier不需要接受参数(供应者，有输出无输入)
 *
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<String, Integer> toInteger = Integer::valueOf;
//        Function<String, String> backToString = toInteger.andThen(String::valueOf);
//        backToString.apply("123");     // "123"

//        Function<Integer, Integer> name = e -> e * 2;
//        Function<Integer, Integer> square = e -> e * e;
//        int value = name.andThen(square).apply(3);  // 36

        // Supplier不需要接受参数(供应者，有输出无输入)
//        Supplier<String> supplier = () -> "special type value";
//
//        String s = supplier.get();
//        System.out.println(s);

        ArrayList<String> strings = new ArrayList<>(Arrays.asList("1", "2", "3", "4","e"));
        List<Integer> resultList = prase(strings, (e) -> { return Integer.valueOf(e); });

        resultList.forEach((e)->{
            System.out.println(e);
        });
    }

    /**
     * 通用的将T 类型转为R类型
     * @param stringList
     * @param function
     * @return
     */
    private static <T,R> List<R> prase(List<T> stringList,Function<T, R> function){
        ArrayList<R> resultList = new ArrayList<>();
        for (T s : stringList) {
            resultList.add(function.apply(s));
        }
        return resultList;
    }
}
