package com.coolron.stream.transform;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xf
 * @Date: 2019/1/24 15:55
 * @Description:
 *
 * sorted 中间操作,返回顺序排列的流视图
 *
 * 仅返回流视图,并没有真正操作后面集合的顺序,集合的顺序没有受任何影响。
 */
public class Sorted {

    public static void main(String[] args) {
//        Random random = new Random();
//        random.ints().limit(10).sorted().forEach(System.out::println);

        List<Integer> list = Arrays.asList(1, 5, 3, 6, 2);
        //list.stream().sorted().forEach(System.out::println);

        list.stream().sorted().map((param) -> param * param ).forEach((e) -> System.out.println(e));
    }
}
