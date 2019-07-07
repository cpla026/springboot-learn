package com.coolron.stream.terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: xf
 * @Date: 2019/1/24 18:34
 * @Description:
 *
 * match 匹配操作 接收predicate接口参数,所有匹配操作都是终止操作,返回布尔结果
 *
 * 查找与匹配
 * allMatch——检查是否匹配所有元素
 * anyMatch——检查是否至少匹配一个元素
 * noneMatch——检查是否没有匹配的元素
 * findFirst——返回第一个元素
 * findAny——返回当前流中的任意元素
 * count——返回流中元素的总个数
 * max——返回流中最大值
 * min——返回流中最小值
 *
 */
public class MatchTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        boolean anyStartsWithA1 = list.stream().allMatch((s) -> s.startsWith("a")); // false
        boolean anyStartsWithA2 = list.stream().anyMatch((s) -> s.startsWith("b")); // true
        boolean anyStartsWithA3 = list.stream().noneMatch((s) -> s.startsWith("d"));// true
        Optional<String> first = list.stream().findFirst();
        Optional<String> any = list.stream().findAny();


        System.out.println(anyStartsWithA1);      // true
        System.out.println(first.get());
        System.out.println(any.get());
    }

}
