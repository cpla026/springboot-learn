package com.coolron.stream.transform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Auther: xf
 * @Date: 2019/1/24 15:53
 * @Description:
 *
 * filter 中间操作
 * 接收predicate接口参数，通过设置的条件，过滤流中的元素
 *
 */
public class Filter {

    public static void main(String[] args) {
        // 获取集合中非空字符串的数量
        List<String> lists = Arrays.asList("How`s", "", "it", "", "going?","","Ron!");
        List<String> newList = new ArrayList<>();
        for (String list : lists) {
            if(!list.isEmpty()){
                newList.add(list);
            }
        }
        int size = newList.size();

        // 使用 stream 简化操作
        Predicate<String> predicte = str -> !str.isEmpty();
        long count = lists.stream().filter(predicte).count();
        System.out.println(count);

    }
}
