package com.coolron.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 对应自定义注解继承ConstraintValidator的实现类
 *
 * 自定义验证实现类里面有两个方法，分别是初始化验证消息、执行验证
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {

    //临时变量保存flag值列表
    private String values;

    /**
     * 初始化values的值
     * 初始化验证消息方法内你可以得到配置的注解内容，而验证方法则是你的验证业务逻辑。
     */
    @Override
    public void initialize(FlagValidator flagValidator) {
        //将注解内配置的值赋值给临时变量
        this.values = flagValidator.values();
    }

    //实现验证  即验证业务逻辑
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext)
    {
        //分割定义的有效值
        String[] value_array = values.split(",");
        boolean isFlag = false;
        //遍历比对有效值
        for (int i =0;i<value_array.length;i++)
        {
            //存在一致跳出循环，赋值isFlag=true
            if(value_array[i].equals(value))
            {
                isFlag = true;
                break;
            }
        }
        //返回是否存在boolean
        return isFlag;
    }
}
