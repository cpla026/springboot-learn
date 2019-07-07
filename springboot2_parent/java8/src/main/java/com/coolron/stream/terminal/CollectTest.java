package com.coolron.stream.terminal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: xf
 * @Date: 2019/1/24 17:13
 * @Description:
 */
public class CollectTest {

    public static void main(String[] args) {
        List<String> lists = Arrays.asList("How`s ", "", "it ", "", "going? ","","Ron!");

        List<String> collected = lists.stream().filter((str) -> !str.isEmpty()).collect(Collectors.toList());
        collected.forEach(System.out::print);
    }

}
