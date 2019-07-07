package com.coolron.stream.transform;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: xf
 * @Date: 2019/1/24 15:51
 * @Description:
 *
 * map 中间操作 接收Function 接口参数
 *
 * 通过传递的函数转换每个元素
 *
 *
 */
public class Map {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
    }
}
