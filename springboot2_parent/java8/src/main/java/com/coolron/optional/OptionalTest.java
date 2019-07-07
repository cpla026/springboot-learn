package com.coolron.optional;

import java.util.Optional;

/**
 * @Auther: xf
 * @Date: 2019/1/29 11:18
 * @Description: Optional 的三种构造方式:
 * 1、Optional.of(obj): 它要求传入的 obj 不能是 null 值的, 否则还没开始进入角色就倒在了 NullPointerException 异常上了.
 * 2、Optional.ofNullable(obj): 它以一种智能的, 宽容的方式来构造一个 Optional 实例. 来者不拒, 传 null 进到就得到 Optional.empty(), 非 null 就调用 Optional.of(obj).
 * <p>
 * Optional 相关方法
 * 1、ifPresent() 与 obj != null 无任何分别
 * 2、orElse("默认值") 存在就返回，没有就返回默认值
 * 3、orElseGet("lambda 表达式") 存在就返回，没有通过lambda函数获取
 * 4、map("接口函数") 有值调用函数处理值，无值返回空的optional 获取有多层级关联关系字段的值、或者需要对获取到的值做进一步处理
 * 5、flatMap() 如果有值，为其执行mapping函数返回Optional类型返回值，否则返回空Optional
 * flatMap方法与map方法类似，区别在于mapping函数的返回值不同。map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional。
 * 6、filter() 如果值存在，并且这个值匹配给定的 predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional
 * 7、orElseThrow() 在有值时直接返回, 无值时抛出想要的异常
 */
public class OptionalTest {

    public static void main(String[] args) {

        User user = new User();
        user.setId("1");
        user.setUserName("Ron");
        user.setGender("female");
        City city = new City("1", "shenzhen");

        user.setCity(city);
        //String nameOptional = (String)getNameOptional(null);

        Object cityName = getCity(user,"female");

        System.out.println(cityName);
    }

    /**
     * 根据用户获取用户所在的城市名字，性别为过滤条件
     */
    private static String getCityName(User user, String sex){
        if (null != user){
            String gender = user.getGender();
            if(null != gender && sex.equals(gender)){
                if(null != gender){
                    String userName = user.getUserName();
                    if(null != userName){
                        City city = user.getCity();
                        if(null != city){
                            String cityName = city.getCityName();
                            if(null != cityName){
                                return cityName;
                            }else{
                                return "UnKnown";
                            }
                        }else{
                            return "UnKnown";
                        }
                    }else{
                        return "UnKnown";
                    }
                }else{
                    return "UnKnown";
                }
            }else{
                return "UnKnown";
            }
        }else{
            return "UnKnown";
        }
    }

    public static Object getNameOptional(User u) {

        /**
         * user不为空返回Optional<User>，为空返回空的Optional
         */
        Optional<User> user = Optional.ofNullable(u);
//
//        if (xuansi.isPresent()) {
//            return xuansi.get().getUserName();
//        } else {
//            return "UnKnown";
//        }

        // isPresent() 与 obj != null 无任何分别
        // Optional.of(u).ifPresent(System.out::println);

        //而不是 return xuansi.isPresent() ? xuansi.get() : null;
        //return xuansi.orElse(null);
        return user.orElseThrow(() -> new NullPointerException("没有值，抛出异常"));

        //而不要 return xuansi.isPresent() ? xuansi: fetchAUserFromDatabase();
        //return xuansi.orElseGet(() -> fetchAUserFromDatabase());

        //return xuansi.filter((e) -> "female".equals(e.getGender())).map(e -> e.getUserName()).orElse("UnKnown");

    }

    /**
     * 获取 xuansi 关联的 city的信息
     */
    public static Object getCity(User u, String sex) {
        // map 可能无线级联
        //return Optional.ofNullable(u).filter(xuansi -> sex.equals(xuansi.getGender())).map(e -> e.getCity()).map(City::getCityName).orElse("UnKnown");
        return Optional.ofNullable(u).filter(user -> "ron".equals(user.getUserName())).map(User::getCity).map(City::getCityName).orElseThrow(() -> new NullPointerException("没有值，抛出异常"));
    }

}
