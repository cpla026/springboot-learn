package com.coolron.webmvcconfig.new_jdk;

/**
 * @Auther: xf
 * @Date: 2019/1/8 19:08
 * @Description: 社交接口 测试jdk1.8 新特性
 */
public interface SocialInterface {
    default int getAge(){ return 25; }
    String getGender();
}
