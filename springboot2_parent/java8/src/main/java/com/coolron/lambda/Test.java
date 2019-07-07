package com.coolron.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: xf
 * @Date: 2019/1/10 10:49
 * @Description:
 */
public class Test {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        list.stream().filter((e) -> !"a".equals(e)).forEach(System.out::println);
        List<String> stringList = list.stream().filter((e) -> !"a".equals(e)).collect(Collectors.toList());

//        list.forEach((s) -> System.out.print(s + ";"));
//        list.forEach(System.out::print);

        // 容器删除
//        Iterator<String> it = list.iterator();
//        while(it.hasNext()){
//            String next = it.next();
//            if("a".equals(next)){
//                it.remove();
//            }
//        }
//
//        list.removeIf(new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return "a".equals(s);
//            }
//        });
//
//        list.removeIf((str) -> str.equals("a"));


        // java1.8 为了扩充匿名方法，追加的新特性
        // java 中使用匿名方法必须要对应接口中的一个抽象方法  此接口称之为函数式接口
//        Runnable runnable1 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("aaaaaaa");
//            }
//        };
//        Runnable runnable2 = () -> {
//            System.out.println("bbbbbbbbbb");
//        };
//
//        runnable1.run();
//        runnable2.run();

    }
}
