package com.coolron.defaultinterface;

/**
 * @Auther: xf
 * @Date: 2019/3/22 11:20
 * @Description:
 */
public interface Interface2 {

    void test();
    default String heart(){
        return "SW";
    };
}
