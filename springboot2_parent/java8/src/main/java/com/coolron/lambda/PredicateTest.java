package com.coolron.lambda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Auther: xf
 * @Date: 2019/1/10 09:59
 * @Description: Predicate接口
 *
 * Predicate 接口只有一个参数，返回boolean类型。
 * 该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）
 *
 */
public class PredicateTest {

    public static void main(String[] args) {
//        Predicate<String> predicate = (s) -> s.length() > 0;
//
//        // 判断是否满足上面的条件，满足返回true，不满足返回false
//        predicate.test("foo");              // true
//        predicate.negate().test("foo");     // false
//
//        // 判断对象是否非空，非空返回true，否则返回false
//        Predicate<Object> nonNull = Objects::nonNull;
//        Predicate<Boolean> isNull = Objects::isNull;
//
//        Predicate<String> isEmpty = String::isEmpty;
//        Predicate<String> isNotEmpty = isEmpty.negate();

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

//        for (String s : list) {
//            list.remove(s);
//        }


        delete(list, (e) -> { return e.equals("a");});

        // JDK 自带
//        list.removeIf((str) -> str.equals("a"));

    }

    /**
     * 删除集合的通用操作
     * @param lists
     * @param predicate
     * @param <T>
     */
    private static <T> void delete(List<T> lists, Predicate<T> predicate){
        Iterator<T> iterator = lists.iterator();
        while(iterator.hasNext()){
            if(predicate.test(iterator.next())){
                iterator.remove();
            }
        }
    }


}
