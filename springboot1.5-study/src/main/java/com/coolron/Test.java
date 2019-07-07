package com.coolron;/**
 * Created by Administrator on 2019/5/19.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xf
 * @Date: 2019/5/19 19:49
 * @Description:
 */
public class Test {

    public static void main(String[] args) {

        List list = new ArrayList();
        list.add(123);
        list.add("abc");

        //String str1 = "hello";
        //String str2 = "" + str1;
        //String str3 = str1.intern();
        //System.out.println(str1 == str2);
        //System.out.println(str1 == str3);

        //BigDecimal b1 = new BigDecimal(0.1);
        //BigDecimal b2 = new BigDecimal("0.1");
        //BigDecimal b3 = BigDecimal.valueOf(0.1);
        //System.out.println(b1);
        //System.out.println(b2);
        //System.out.println(b3);

        //String a = "a";
        //int a1 = a.hashCode();

       // final StringBuffer a = new StringBuffer("111");
       // final StringBuffer b = new StringBuffer("222");
       // a=b;//此句编译不通过
        // final StringBuffer a = new StringBuffer("111");
       // a.append("222");// 编译通过
       // String a = "ab";
       // String bb = "b";
       // String b = "a" + bb;
       // System.out.println((a == b)); //result = false

        //String a = "ab";
        //final String bb = getBB();
        //String b = "a" + bb;
        //System.out.println((a == b)); //result = false
    }

    private static String getBB() {
        return "b";
    }

}
