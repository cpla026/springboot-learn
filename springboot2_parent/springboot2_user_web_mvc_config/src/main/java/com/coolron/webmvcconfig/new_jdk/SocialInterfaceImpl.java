package com.coolron.webmvcconfig.new_jdk;

/**
 * @Auther: xf
 * @Date: 2019/1/8 19:11
 * @Description:
 */
public class SocialInterfaceImpl implements SocialInterface {
    @Override
    public String getGender() {
        return "female";
    }
    /**
     * 覆盖接口默认实现
     */
   /* @Override
    public int getAge(){
        return 70;
    }*/
    public static void main(String[] args) {
        SocialInterfaceImpl social = new SocialInterfaceImpl();
        System.out.println("年龄：" + social.getAge());     // 年龄：25
        System.out.println("性别：" + social.getGender());  // 性别：female
    }
}
