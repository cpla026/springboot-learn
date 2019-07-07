package com.coolron.defaultinterface;

/**
 * @Auther: xf
 * @Date: 2019/3/22 11:22
 * @Description:
 */
public class Person implements Interface1,Interface2 {

    @Override
    public void test() {
        System.out.println("test");
    }

}
