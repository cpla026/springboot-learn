package com.coolron.stream.terminal;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: xf
 * @Date: 2019/1/24 18:38
 * @Description:
 *
 * reduce 终止操作,
 *
 * 根据指定函数在流元素上执行reduction操作,返回Optional对象,包括执行结果
 *
 * 可以将流中元素反复结合起来，得到一个值。
 */
public class ReduceTest {

    public static void main(String[] args) {
        List<String> lists = Arrays.asList("How`s", "", "it", "", "going?","","Ron!");

        // Optional ：JDK1.8 新特性
        Optional<String> reduce = lists.stream().filter((str) -> !str.isEmpty()).reduce((e1, e2) -> e1 + " ; " + e2);
        // 是否存在元素
        boolean present = reduce.isPresent();
        if(present){
            // 获取元素
            String s = reduce.get();
            System.out.println(s);
        }
    }
}
