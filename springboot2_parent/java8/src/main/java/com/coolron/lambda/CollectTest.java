package com.coolron.lambda;

import com.coolron.lambda.funinterface.MyCollectors;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: xf
 * @Date: 2019/1/30 09:57
 * @Description: collect 收集器
 *
 * 示例：collect(toList()) 将流生成列表
 *
 */
public class CollectTest {

    public static Map<String, Object> map = new HashMap<>();

    public static void main(String[] args) {

        ArrayList<Integer> lists = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        //getValue(lists);

//        collectToMap().forEach((key,value) -> {
//            System.out.println(key + ": "+ value);
//        });

        //Map<String, List<Person>> stringListMap = groupingByList();
//        String s = joiningList(lists);
//        System.out.println(s);

//        CollectTest collectTest = new CollectTest();
//
//        Object key = collectTest.getValueFrom("key");
//        System.out.println(key);

//        mapForeach();

        groupingByList().forEach((key,value) -> {
            System.out.println(key + ": "+ value);
        });
    }

    /**
     * computeIfAbsent：如果不存在就使用 lambda 计算新的值
     */
    private Object getValueFrom(String key){
        return map.computeIfAbsent(key,this::getStr);
    }
    private String getStr(String name){
        return name.concat("aaaa");
    }

    /**
     * map 的迭代
     */
    private static void mapForeach(){
        Map<String, Person> maps = new HashMap<>();
        maps.put("1",new Person("ron1", "w1"));
        maps.put("2",new Person("ron2", "w2"));
        maps.put("3",new Person("ron3", "w3"));

        /**
         * 普通方法
         */
        for (Map.Entry<String, Person> entry : maps.entrySet()) {
            String key = entry.getKey();
            Person person = entry.getValue();
        }

        /**
         * java 8 中方法
         */
        maps.forEach((key,value) -> {
            System.out.println(key);
            System.out.println(value.toString());
        });

    }

    /**
     * collect 的基本操作
     */
    private static Object basicOperation(ArrayList<Integer> lists){
        List<Integer> collectList = lists.stream().collect(Collectors.toList());
        Set<Integer> collectSet = lists.stream().collect(Collectors.toSet());
        TreeSet<Integer> collectTreeSet = lists.stream().collect(Collectors.toCollection(TreeSet::new));
        return collectList;
    }

    private static Map<String, Object> collectToMap(){
        List<Person> lists = new ArrayList();
        lists.add(new Person("ron1", "w1"));
        lists.add(new Person("ron2", "w2"));
        lists.add(new Person("ron2", null));
        lists.add(new Person("ron3", "w3"));
        lists.add(new Person("ron3", "w4"));
        /**
         * 该方法，key重复和value为null时都会抛异常
         * 解决方法如下三种：
         */
        //Map<String, String> collect = lists.stream().collect(Collectors.toMap(Person::getFirstName, Person::getLastName));
        // 1、解决重复 key 的问题 该方法还是会有value为null的问题
        //Map<String, String> collect = lists.stream().collect(Collectors.toMap(Person::getFirstName, Person::getLastName,(oldValue,newValue) -> oldValue));
        // toMap方法还有一个重载方法，指定一个Map的具体实现
        //Map<String, String> collect = lists.stream().collect(Collectors.toMap(Person::getFirstName, Person::getLastName,(oldValue,newValue) -> oldValue,HashMap::new));
        // 2、collect 重载的方法。
        //Map<String, Object> collect = lists.stream().collect(HashMap::new, (m, v) -> {m.put(v.getFirstName(), v.getLastName());}, HashMap::putAll);

        // 3、自定义collector  参考：http://blog.jobbole.com/104067/
        Map<String, Object> collect = lists.stream().collect(MyCollectors.toMap(Person::getFirstName, Person::getLastName));

        return collect;
    }

    /**
     * 最大、最小、平均值
     */
    private static Object getValue(ArrayList<Integer> lists){
        Optional<Integer> collectMax = lists.stream().collect(Collectors.maxBy(Comparator.comparing((e) -> e)));
        Optional<Integer> collectMin = lists.stream().collect(Collectors.minBy(Comparator.comparing((e) -> e)));
        Double collectAvg = lists.stream().collect(Collectors.averagingInt(e -> e));
        System.out.println(collectAvg);
        return collectAvg;
    }

    /**
     * 将流分为两个集合
     * partitioningBy 收集器
     * 接收一个stream 返回一个Map  true、false为key
     */
    private static Object partitioningByList(ArrayList<Integer> lists){
        // 大于等于3 作为条件将lists分为两个集合
        Map<Boolean, List<Integer>> booleanListMap = lists.stream().collect(Collectors.partitioningBy(e -> e >= 3));
        System.out.println(booleanListMap.get(true));
        System.out.println(booleanListMap.get(false));
        return booleanListMap;
    }

    /**
     * 数据分组
     * 类似 SQL group by
     */
    private static Map<String, List<Person>> groupingByList(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("ron1", "w1"));
        personList.add(new Person("ron2", "w2"));
        personList.add(new Person("ron2", "w22"));
        personList.add(new Person("ron3", "w3"));

        Map<String, List<Person>> collect = personList.stream().collect(Collectors.groupingBy(e -> e.firstName));
        /**
         * 组合收集器
         */
        Map<String, Long> collect1 = personList.stream().collect(Collectors.groupingBy(e -> e.firstName, Collectors.counting()));
        return collect;
    }

    /**
     * 字符串操作
     * Collectors.joining("间隔符","prefix","suffix") 参数可选
     */
    private static String joiningList(ArrayList<Integer> lists){
        // 1234
        //String collect = lists.stream().map(e -> String.valueOf(e)).collect(Collectors.joining());
        // [1,2,3,4]
        String collect = lists.stream().map(e -> String.valueOf(e)).collect(Collectors.joining(",","[","]"));
        return collect;
    }

}
