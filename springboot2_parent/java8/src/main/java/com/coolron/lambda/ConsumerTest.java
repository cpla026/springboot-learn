package com.coolron.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Auther: xf
 * @Date: 2019/1/10 10:10
 * @Description: Consumer 接口
 *
 * Consumer 提供了一个 accept 抽象函数，该函数接收参数并依据传递的行为应用传递的参数值，
 *
 * 代表了在单一的输入参数上需要进行的操作。和Function不同的是，Consumer没有返回值(消费者，有输入，无输出)
 */
public class ConsumerTest {

    public static void main(String[] args) {
       /* Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));

        Consumer<Integer> add5 = (p) -> {
            System.out.println("old value:" + p);
            p = p + 5;
            System.out.println("new value:" + p);
        };
        add5.accept(10);*/

        List<String> lists = new ArrayList<>();
        lists.addAll(Arrays.asList("hello"," ron!", " how`s"," it"," going?"));
        forEach(lists,(e) ->  System.out.print(e));

    }

    private static <T> void forEach(List<T> lists, Consumer<T> consumer){
        for (T value : lists) {
            // 应用行为
            consumer.accept(value);
        }
    }

}
