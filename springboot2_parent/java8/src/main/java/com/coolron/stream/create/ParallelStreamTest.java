package com.coolron.stream.create;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xf
 * @Date: 2019/1/25 10:00
 * @Description:
 *
 * parallelStream 并行流
 *
 * 并行流操作属于多线程执行
 *
 */
public class ParallelStreamTest {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
    }
}
