package com.coolron.lambda;

import java.util.Comparator;

/**
 * @Auther: xf
 * @Date: 2019/1/10 10:14
 * @Description: Comparator 接口
 */
public class T4 {

    public static void main(String[] args) {
//        Comparator<Person> comparator = (p1, p2) -> p1.lastName.compareTo(p2.firstName);
//        Person p1 = new Person("John", "Doe");
//        Person p2 = new Person("Alice", "Wonderland");
        
        Comparator<Integer> comparator = (p1, p2) -> p1.compareTo(p2);
        Integer p1 = 3;
        Integer p2 = 10;
        
        int compare = comparator.compare(p1, p2);// > 0
        int compare1 = comparator.reversed().compare(p1, p2);// < 0
    }

}
