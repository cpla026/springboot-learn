package com.coolron.service;/**
 * Created by Administrator on 2019/8/25.
 */

/**
 * @Auther: xf
 * @Date: 2019/8/25 15:00
 * @Description: 自定义的starter提供的服务
 * 通过 heart 方法获取值
 */
public class MyStarterService {

    private MyStarterProperties starterProperties;

    public MyStarterService(){}
    public MyStarterService(MyStarterProperties starterProperties){
        this.starterProperties = starterProperties;
    }

    public String heart(){
        return "heart you!" + starterProperties.getName();
    }
}
